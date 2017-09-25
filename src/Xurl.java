import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Xurl {
	
	public static void main (String[] args){
		String url=args[0];
		Socket _socket_ = null;
		try {
			/*Verify URL*/
			MyURL _url_ = new MyURL(url);
			
			/*Create a socket*/
			
			int port = _url_.getPort(); /// Case when port wasn't specify
			if (port == -1 ) {
				port = 80; 
			}
			
			String path = _url_.getPath();
			String host = _url_.getHost();
			_socket_ = new Socket(_url_.getHost(), port);
			
			/* Output */
			OutputStream out = _socket_.getOutputStream();
			PrintStream output = new PrintStream(out);
			String request = "GET "+ path + " HTTP/1.1\r\n";
			request += "Host: " + host + "\r\n";
			request +="\r\n";
			System.out.println(request);
			output.print(request);
			

			/*Input*/
			InputStream in = _socket_.getInputStream();
			InputStreamReader in_reader = new InputStreamReader(in);
			BufferedReader bufferedreader = new BufferedReader(in_reader);
			
			String line = new String();
			line = bufferedreader.readLine();
			
			String[] parsed_line = line.split(" ", 3);
			/*Code determination*/
			int code = Integer.parseInt(parsed_line[1]);
			if (code == 200) {
				System.err.println(parsed_line[2]);
			}
			else if (code == 301){
				System.err.println(parsed_line[2]);
				System.out.println("You will be redirected to your page");
			}
			else if (code == 302){
				System.err.println(parsed_line[2]);
				System.out.println("You will be redirected to your page");
			}
			else if (code == 400){
				System.err.println(parsed_line[2]);
				System.out.println("Please retry with a correct address");
			}
			else if (code == 404){
				System.err.println(parsed_line[2]);
				System.out.println("Page not found, please retry");
			}
			
			while (bufferedreader.ready()) {
				if ((line = bufferedreader.readLine()) != null){
					System.out.println(line);
				}
			}
			System.out.println("File downloaded");
			/*Closing connection*/
			request = "CONNECTION: Close";
			output.print(request);
			
			output.close();
			bufferedreader.close();
			_socket_.close();
			
		}
		catch(ConnectException e) {
			/*Exception from MyURL*/
			System.err.println(e.getMessage());
			System.out.println("Unknown host");
		}	
		catch(UnknownHostException e) {
			/*Exception from MyURL*/
			System.err.println(e.getMessage());
			System.out.println("Unknown host");
		}	
		catch(IOException e) {
			/*Exception from the Socket creation*/
			if (_socket_ != null) {
				try {
					_socket_.close();
				}
				catch(IOException ioclose) {
					System.err.println(ioclose.getMessage());
				}
			}
		}
		catch(IllegalArgumentException e) {
			/*Exception from MyURL*/
			System.err.println(e.getMessage());
			System.out.println("Your URL might not be well formated");
		}
	}
}