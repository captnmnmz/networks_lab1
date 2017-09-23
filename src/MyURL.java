
public class MyURL {
	public String protocol;
	public String hostname;
	public int port;
	public String path;
	
	public MyURL(String url) {
		
		
		String[] parsed = url.split(":");
		int parsed_len=parsed.length;
		
		protocol=parsed[0];
		if (parsed_len<3){
			port=-1;
			String[] parsed_ = parsed[1].split("/");
			hostname=parsed_[2];
			if (parsed_.length ==3) {
				path="/";
			}
			else {
				path="/"+parsed_[3];
			}
		}
		
		else {
			String[] parsed_ = parsed[1].split("/");
			String[] _parsed_= parsed[2].split("/");
			hostname=parsed_[2];
			port=Integer.parseInt(_parsed_[0]);
			if (_parsed_.length ==1) {
				path="/";
			}
			else {
				path="/"+_parsed_[1];
			}
			
		}
		
	}
	
	public String getProtocol() {
		return protocol;
	}
	
	public String getHost() {
		return hostname;
	}
	
	public int getPort() {
		return port;
	}
	
	public String getPath() {
		return path;
	}

}
