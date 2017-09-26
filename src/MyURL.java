
public class MyURL {
	public String protocol;
	public String hostname;
	public int port;
	public String path;
	
	public MyURL(String url) throws IllegalArgumentException {
		

// This first code bloc checks the validity of the structure of the URL
		
		int host_index=url.indexOf("://");
		
		if (host_index==-1) {
			throw new IllegalArgumentException(":// missing after between protocol and hostname");
		}else {
		
			protocol = url.substring(0, host_index);
			// We add 3 to the index because it is the index of the first character of ://
			
			if (url.substring(host_index+3).isEmpty()) {
				throw new IllegalArgumentException("no hostname and path");
			}else{
				if (!(url.substring(host_index+3).contains("/"))) {
					throw new IllegalArgumentException("/ missing in path field");
				}
				String[] parsed = (url.substring(host_index+3)).split("/",2);
				if (!parsed[1].isEmpty()) {
					path = "/"+parsed[1];
				}else {
					path = "/";
				}
				if (parsed[0].contains(":")){
					port=Integer.parseInt((parsed[0].split(":",2))[1]);
					hostname=parsed[0].split(":")[0];
				}else {
					port = -1;
					hostname=parsed[0];
				}
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

