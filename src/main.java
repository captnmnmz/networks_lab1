
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String protocol,host,path;
		int port;
		String test_url="http://host:80:/path/file";
		try {
			MyURL _myurl= new MyURL(test_url);
			protocol=_myurl.getProtocol();
			host=_myurl.getHost();
			port=_myurl.getPort();
			path=_myurl.getPath();
			
			System.out.println(protocol);
			System.out.println(host);
			System.out.println(port);
			System.out.println(path);
			
		}catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		


	}

}
