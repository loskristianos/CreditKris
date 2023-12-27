/*  Intermediate layer between UI views and database DataHandlers. Takes objects returned from the database (ArrayLists for the most part), and prepares them
    into a nice format for the UI classes to display, and vice versa. So it should work with any UI.

    This might end up not being necessary, the plan was (and still is) for the abstract DataHandler class to
    play that role, this is just a precaution in case the UI classes still end up getting
    database-specific stuff in them when I start writing them.
 */

package controller;

public class ViewController {
}
