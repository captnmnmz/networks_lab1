import java.net.Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Xurl {
	
	public Xurl (String url) {
		System.out.println("test");
		Socket _socket_ = null;
		try {
			/*Verify URL*/
			MyURL _url_ = new MyURL(url);
			
			/*Create a socket*/
			
			int port = _url_.port; /// Case when port wasn't specify
			if (port == -1 ) {
				port = 80; 
			}
			_socket_ = new Socket(_url_.hostname, port); ///What happens when port == -1 ?
			
			/* Output */
			OutputStream out = _socket_.getOutputStream();
			PrintStream output = new PrintStream(out);
			output.print("GET"+ _url_.path + " HTTP/1.0\r\n");

			/*Input*/
			InputStream in = _socket_.getInputStream();
			InputStreamReader in_reader = new InputStreamReader(in);
			BufferedReader bufferedreader = new BufferedReader(in_reader);
			String line = new String();
			while ((line = bufferedreader.readLine()) != null) {
				String[] parsed_line = line.split(" ", 3);
				System.out.println(parsed_line);
				/*Code determination*/
				int code = Integer.parseInt(parsed_line[1]);
				if (code == 200) {
					System.out.println(parsed_line[2]);
				}
				else if (code == 301){
					System.out.println(parsed_line[2]);
					System.out.println("You will be redirected to your page");
				}
				else if (code == 302){
					System.out.println(parsed_line[2]);
					System.out.println("You will be redirected to your page");
				}
				else if (code == 400){
					System.out.println(parsed_line[2]);
					System.out.println("Please retry with a correct address");
				}
				else if (code == 404){
					System.out.println(parsed_line[2]);
					System.out.println("Page not found, please retry");
				}
			}		
		}
		catch(IOException e) {
			/*Exception from the Socket creation*/
			if (_socket_ != null) {
				try {
					_socket_.close();
				}
				catch(IOException ioclose) {
					System.out.println(ioclose.getMessage());
				}
			}
		}
		catch(IllegalArgumentException e) {
			/*Exception from MyURL*/
			System.out.println(e.getMessage());
		}		
	}
}