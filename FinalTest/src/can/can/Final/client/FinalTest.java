package can.can.Final.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.user.client.Window;



public class FinalTest implements EntryPoint {
	
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	public void onModuleLoad() {
		addGoogleAuth();
		}
		
	  private static final Auth AUTH = Auth.get();
	  private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";

	  // This app's personal client ID assigned by the Google APIs Console	  
	  private static final String GOOGLE_CLIENT_ID = "768408804239.apps.googleusercontent.com";
	  
	  // The auth scope being requested. This scope will allow the application to
	  // identify who the authenticated user is.
	  private static final String PLUS_ME_SCOPE = "https://docs.google.com/feeds/";
	  
	  // Adds a button to the page that asks for authentication from Google.
	  private void addGoogleAuth() {
		  
	    // Since the auth flow requires opening a popup window, it must be started
	    // as a direct result of a user action, such as clicking a button or link.
	    // Otherwise, a browser's popup blocker may block the popup.
	    Button button = new Button("Authenticate with Google");
	    button.addClickHandler(new ClickHandler() {
	      
	      @Override
	      public void onClick(ClickEvent event) {
	         final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
	            .withScopes(PLUS_ME_SCOPE);
	        // Calling login() will display a popup to the user the first time it is
	        // called. Once thes user has granted access to the application,
	        // subsequent calls to login() will not display the popup, and will
	        // immediately result in the callback being given the token to use.
	        AUTH.login(req, new Callback<String, Throwable>() {
	          @Override
	          public void onSuccess(String token) {
	            Window.alert("Got an OAuth token"+"\n"
	            		+"Press OK to start the file upload automatically(page1.png file hardcoded)" );
	            greetingService.greetServer(token,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
							Window.alert("Upload Failed"+caught.getMessage());
							}

							public void onSuccess(String result) {
								Window.alert("Upload status: "+result);
							}
						});
	          }

	          @Override
	          public void onFailure(Throwable caught) {
	            Window.alert("Error:\n" + caught.getMessage());
	          }
	        });
	      }
	    });
	    RootPanel.get("button").add(button);
	  }
	
}
