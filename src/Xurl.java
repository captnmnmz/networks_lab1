import java.net.Socket;
import java.io.IOException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Xurl {
	
	public Xurl (String url) throws IOException {
		Socket _socket_ = null;
		try {
			/*Verify URL*/
			MyURL _url_ = new MyURL(url);
			
			/*Create a socket*/
			_socket_ = new Socket(_url_.hostname,_url_.port); ///What happens when port == -1 ?
			
			OutputStream out = _socket_.getOutputStream();
			PrintStream output = new PrintStream(out);
			output.print("GET"+ _url_.path + " HTTP/1.0\r\n");
		
			InputStream in = _socket_.getInputStream();
			InputStreamReader in_reader = new InputStreamReader(in);
			BufferedReader bufferedreader = new BufferedReader (in_reader);
			String line = bufferedreader.readLine();
			while (line  != null) {
				/*Test code*/
				int code = 
				/*Read new line*/
				line = bufferedreader.readLine();
			}
			
			
			/*Create data streams*/
			
		
				
				
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