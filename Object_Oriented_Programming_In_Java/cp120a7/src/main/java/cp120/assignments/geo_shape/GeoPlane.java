package cp120.assignments.geo_shape;

import cp120.d_list.DList;
import cp120.d_list.DNode;

/**
 * A simple drawing surface for displaying GeoShapes.
 * @author jcrowley
 */
public class GeoPlane {
    DList list = new DList();

    /**
     * Adds a shape to the list of shapes to be drawn on the plane.
     * Note that the shape is not immediately drawn;
     * to draw the shape immediately, call the redraw() method.
     * @param shape the GeoShape shape to add to the list.
     */
    public void addShape( GeoShape shape){
        list.addTail(new DNode(shape));
    }

    /**
     * Removes a shape from the list of shapes to be drawn on the plane.
     * Note that the plane is not immediately redrawn;
     * to redraw the plane immediately, call the redraw() method.
     * @param shape the GeoShape shape to remove from the list.
     * @return boolean value, true, if shape is found and removed; false, otherwise.
     */
    public boolean removeShape(GeoShape shape){
        boolean found = false;
        DNode node = list.getHead();
        while (!found && (node != list)) {
            if (node.getData() == shape) {
                node.remove();
                found = true;
            }
        }
        return found;
    }

    /**
     * Redraw the plane.
     */
    public void redraw() {
        GeoShape shape;
        DNode node = list.getHead();
        while (node != list) {
            shape = (GeoShape) node.getData();
            shape.draw(null);
            node = node.getNext();
        }
    }
}
