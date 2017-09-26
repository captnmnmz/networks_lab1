import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;

public class Xurl {
	



	public static void main (String[] args){
		int args_size=args.length;
		String url=args[0];
		
		Socket _socket_=null;

		try {
			/*Verify URL*/
			MyURL _url_ = new MyURL(url);
			
			
			int port = _url_.getPort(); 
			/// Case when port wasn't specify
			if (port == -1 ) {
				port = 80; 
			}
			
			String path = _url_.getPath();
			String host = _url_.getHost();
			String webFile=path.substring(path.lastIndexOf('/')+1,path.length());
				
			String param=null;
			String param2=null;
			
			
			switch (args_size) {
			/**
			 * Differentiate the different cases :
			 * case 1 : Direct connection to host
			 * case 3 : connect to host via proxy (proxy, proxyport arguments)
			 */
			case 1:
				param=host;
				param2=path;
				break;
			
			case 3:
				param=args[1];
				port=Integer.parseInt(args[2]);
				param2=url;

				break;
			
			default:
				throw new IllegalArgumentException("Wrong args entered");
				
			}
			
			_socket_=new Socket(param,port);
			/* Output */
			OutputStream out = _socket_.getOutputStream();
			PrintStream output = new PrintStream(out);
			String request = "GET "+ param2 + " HTTP/1.1\r\n";
			
			request += "Host: " + host + "\r\n";
			request +="\r\n";
			System.out.println(request);
			output.print(request);
			

			/*Input*/
			InputStream in = _socket_.getInputStream();
			InputStreamReader in_reader = new InputStreamReader(in);
			BufferedReader bufferedreader = new BufferedReader(in_reader);
			
			File received = new File(webFile);
			FileWriter writer = new FileWriter(received);
			
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
					writer.write(line);
				}
			}
			System.out.println("File downloaded");
			/*Closing connection*/
			request = "CONNECTION: Close";
			output.print(request);
			
			writer.flush();
			writer.close();
			
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
			System.out.println("Your URL might not be well formated, or wrong args entered");
		}
	}
	

}