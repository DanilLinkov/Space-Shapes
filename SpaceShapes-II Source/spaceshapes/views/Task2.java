package spaceshapes.views;


import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

public class Task2 extends Task1 implements ShapeModelListener {

    public Task2(ShapeModel shapeModel) {
        super(shapeModel);
    }

    @Override
    public void update(ShapeModelEvent event) {
        // Create the children object array containing the shape
        // that has been removed or added
        Object[] children = new Object[1];
        children[0] = event.operand();

        // Create the source object array containing the shape
        // that sent the request
        Object[] source = new Object[1];
        source[0] = event.source();

        // If the shape does not have a parent then its path is null
        TreePath treePath = null;

        // If it does have a parent then take the path of the parent to the top
        // of the hierarchy
        if(event.parent()!=null)
        {
            treePath = new TreePath(event.parent().path().toArray());
        }

        // The position where the children were when they were removed/added
        int[] childIndices = new int[1];
        childIndices[0] = event.index();

        // Create the new treeModelEvent with the given args
        TreeModelEvent treeModelEvent = new TreeModelEvent(source,treePath,childIndices,children);

        for(TreeModelListener listener : listeners)
        {
            // If the event is to add a shape then send an insert event to the listeners
            if(event.eventType().equals(ShapeModelEvent.EventType.ShapeAdded))
            {
                listener.treeNodesInserted(treeModelEvent);
            }
            // Else send a remove event to the listeners
            else if(event.eventType().equals(ShapeModelEvent.EventType.ShapeRemoved))
            {
                listener.treeNodesRemoved(treeModelEvent);
            }
        }

    }
}
