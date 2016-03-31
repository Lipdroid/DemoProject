package lipdroid.demoproject.library;





import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import lipdroid.demoproject.R;


public class CustomizeDialogOk extends Dialog implements OnClickListener {  
    Button okButton;  
    Context mContext;  
    TextView mTitle = null;  
    TextView mMessage = null;
   
    View v = null; 
    private OnSuccessOkDialogHandler onSuccessOkDialogHandler;
    public CustomizeDialogOk(Context context,OnSuccessOkDialogHandler onSuccessOkDialogHandler) {  
        super(context); 
        this.onSuccessOkDialogHandler=onSuccessOkDialogHandler;
        mContext = context;  
        /** 'Window.FEATURE_NO_TITLE' - Used to hide the mTitle */  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
       
        /** Design the dialog in main.xml file */  
       setContentView(R.layout.customdialogok);
       v = getWindow().getDecorView();  
       v.setBackgroundResource(android.R.color.transparent);  
       
       
       Typeface fontRegular=Typeface.createFromAsset(mContext.getAssets(), "fonts/HelveticaNeue.ttf");
       mTitle = (TextView) findViewById(R.id.dialogTitle);
       mMessage = (TextView) findViewById(R.id.dialogMessage);  
       okButton = (Button) findViewById(R.id.OkButton);

        new SCMultipleScreen(context);
        SCMultipleScreen.resizeAllView((ViewGroup) v);
       
       mTitle.setTypeface(fontRegular);
       mMessage.setTypeface(fontRegular);
       okButton.setTypeface(fontRegular);
    
       
       okButton.setOnClickListener(this);
      
    }  
    public void onClick(View v) {  
        /** When OK Button is clicked, dismiss the dialog */  
        if (v == okButton)
        {
        	dismiss();
           if(mTitle.getText().toString().equals("Prefarence Info")){
        		onSuccessOkDialogHandler.onSuccessMessage(true, 0);	
        	}else {
        		onSuccessOkDialogHandler.onSuccessMessage(true, 0);	
        	}
        	
        }
        
    }  
    @Override  
    public void setTitle(CharSequence title) {  
        super.setTitle(title);  
        mTitle.setText(title);  
    }  
    @Override  
    public void setTitle(int titleId) {  
        super.setTitle(titleId);  
        mTitle.setText(mContext.getResources().getString(titleId));  
    }  
    /**  
     * Set the message text for this dialog's window.  
     *   
     * @param message  
     *      - The new message to display in the title.  
     */  
    public void setMessage(CharSequence message) {  
        mMessage.setText(message);  
        mMessage.setMovementMethod(ScrollingMovementMethod.getInstance());  
    }  
    
    public void setMessage(int messageId) {  
        mMessage.setText(mContext.getResources().getString(messageId));  
        mMessage.setMovementMethod(ScrollingMovementMethod.getInstance());  
    }  
    
    public interface OnSuccessOkDialogHandler {
		/**
		 * this method is called when a date was selected by the user
		 * 
		 * @param view
		 *            the caller of the method
		 * 
		 */
		public void onSuccessMessage(Boolean iSSuccess, long id);
		
	}
}  
