import java.util.Stack;

public class History
{
	
	Stack<Event> eventStorage = new Stack<Event>();
	Stack<Event> undoStorage = new Stack<Event>();
	
    /**
       Notepad will call this function when thier text changes.

       deletion is a boolean that indicates if the action was a deletion of text or the insertion of text.
       position is the postion where the change took place
       Change is the string of characters that is the change.
     */
   public void addEvent(boolean deletion, int position, String Change)
   {
	   Event myEvent = new Event(Change, position, deletion);
	   eventStorage.push(myEvent);
	   undoStorage.clear();
   }


    /**
       Notepad will call this function when it wishes to undo the last event.

       note is a variable to the Notepad that called this function
     */
   public void undoEvent(NotePad note)
   {
	   if (eventStorage.isEmpty()) { return; }
	   
	   Event myEvent = eventStorage.pop();
	   undoStorage.push(myEvent);
	   
	   if (myEvent.deletion) {
		   note.insert(myEvent.position, myEvent.event);
	   } else {
	   		note.remove(myEvent.position, myEvent.event.length());
	   }
	   
   }


    /**
       Notepad will call this function when it wishes to redo the last event that was undone.
       Note that new actions should clear out events that can be "redone"
       note is a variable to the Notepad that called this function
     */
   public void redoEvent(NotePad note)
   {
	   if (undoStorage.isEmpty()) { return; }
	   
   		Event myEvent = undoStorage.pop();
   		eventStorage.push(myEvent);
   		
 	   if (myEvent.deletion) {
		   note.remove(myEvent.position, myEvent.event.length());
	   } else {
		   note.insert(myEvent.position, myEvent.event);   
	   }
	   
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasUndoData()
   {
       return (! undoStorage.isEmpty());
   }

    /**
       returns true if there is undo data in the History
     */
   public boolean hasReDoData()
   {
       return (! eventStorage.isEmpty());
   }
	

}
