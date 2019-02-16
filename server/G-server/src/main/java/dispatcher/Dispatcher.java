package dispatcher;

import common.Message;
import common.constants.Msg;

public final class Dispatcher {
	
	 
	public Dispatcher() {
		
	}
	
	public static void messageFromNetwork(Message message) {
		switch(message.messageType) {
			case Msg.TO_AUTH: messageToAuth(message);
		}
	}

	private static void messageToAuth(Message message) {
		// TODO Auto-generated method stub
		
	}
}
