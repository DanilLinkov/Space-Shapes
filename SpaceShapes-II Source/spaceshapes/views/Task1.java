package spaceshapes.views;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelListener;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class Task1 implements TreeModel {

    // The model that needs to be adapted
    protected ShapeModel _shapeModel;
    // ArrayList for storing the listeners of event objects
    protected ArrayList<TreeModelListener> listeners = new ArrayList<>();

    // Creating a constructor to use the adapter pattern
    public Task1(ShapeModel shapeModel)
    {
        _shapeModel = shapeModel;
    }

    /**
     * Returns the root of the shapeModel
     */
    @Override
    public Object getRoot() {
        return _shapeModel.root();
    }

    /**
     * Returns the child at a given index of the given parent
     */
    @Override
    public Object getChild(Object parent, int index) {

        // If the parent is a Carrier shape then check if the index
        // is in a valid range then find the child
        if(parent instanceof CarrierShape)
        {
            if(((CarrierShape) parent).shapeCount()<=index || index<0)
            {
                return null;
            }
            else
            {
                return ((CarrierShape) parent).shapeAt(index);
            }
        }

        return null;
    }

    /**
     * Returns the number of children of a parent
     */
    @Override
    public int getChildCount(Object parent) {

        // If the parent is a CarrierShape then check its number of children
        if(parent instanceof CarrierShape)
        {
            return ((CarrierShape) parent).shapeCount();
        }

        return 0;
    }

    /**
     * Checks whether a given shape is a leaf node or not
     */
    @Override
    public boolean isLeaf(Object node) {
        // If the shape is not a CarrierShape and has a parent
        return node instanceof Shape && !(node instanceof CarrierShape) && ((Shape) node).parent()!=null;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // EMPTY BODY
    }

    /**
     * Get the index of a child given their parent
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        // If the parent is a CarrierShape and the child is a shape then find the index
        if(parent instanceof CarrierShape && child instanceof Shape)
        {
            return ((CarrierShape) parent).indexOf((Shape) child);
        }

        return -1;
    }

    /**
     * Add and remove listeners
     */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }
}
