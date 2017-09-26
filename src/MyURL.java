public class MyURL {
	public String protocol;
	public String hostname;
	public int port;
	public String path;
	
	public MyURL(String url) throws IllegalArgumentException {
		

// This first code bloc checks the validity of the structure of the URL
		
		int host_index, port_index, path_index;
		host_index=url.indexOf("://");

		if (host_index==-1) {
			throw new IllegalArgumentException(":// missing after between protocol and hostname");
		}else {
		// We add 3 to the index because it is the index of the first character of ://
			port_index=url.indexOf(':',host_index+3);
			if (port_index!=-1) {
				path_index=url.indexOf('/',port_index);
			}else {
				path_index=url.indexOf('/',host_index+3);
			}
			
			if(path_index==-1) {
				throw new IllegalArgumentException("/ missing in path field");
			}else {
	
				String[] parsed = url.split(":");
				int parsed_len=parsed.length;
				
				protocol=parsed[0];
				if (parsed_len<3){
					port=-1;
					/*We remove the first "//" */
					String[] parsed_ = (parsed[1].substring(2)).split("/",2);
					/*It will return "{hostname,path(even if their is / in the path)}*/
					hostname=parsed_[0];
					if (parsed_.length == 1) {
						path="/";
					}
					else {
						path="/"+parsed_[1];
					}
				}
				
				else {
					String[] parsed_= parsed[2].split("/",2);
					hostname=parsed[1].substring(2);
					port=Integer.parseInt(parsed_[0]);
					if (parsed_.length == 1) {
						path="/";
					}
					else {
						path="/"+parsed_[1];
					}
					
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
