package GameStuff.Board;

import java.util.ArrayList;

public class PathNode
{
    private Tile tile;
    private int level;
    private ArrayList<PathNode> options;

    public PathNode(Tile tile, int level)
    {
        this.tile = tile;
        this.level = level;
        options = new ArrayList<>();
    }

    /**
     * gets the tile that this node represents
     * @return the tile that this node represents
     */
    public Tile getTile()
    {
        return tile;
    }

    /**
     * gets the tile equivalent of the node in options at index
     * @param index the index in options to get
     * @return the tile equivalent of the node in options at index
     */
    public Tile getTile(int index)
    {
        return options.get(index).getTile();
    }

    /**
     * adds the given node to this node's options
     * @param node the node to add to this node's options
     */
    public void addOption(PathNode node)
    {
        options.add(node);
    }

    /**
     * Returns the amount of options available in this node
     * @return the amount of options available in this node
     */
    public int numOptions()
    {
        return options.size();
    }

    /**
     * gets the PathNode's level
     * @return the level of the PathNode
     */
    public int getLevel()
    {
        return level;
    }
}
