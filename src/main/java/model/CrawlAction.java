package model;

/** Represents data of last crawling action.
 * This class takes care of storing number of pages visited,
 * number of unique pages visited, time elapsed and
 * also search depth.
 * @version 0.1
 */
public class CrawlAction {
    /**
     * the number of pages visited by the crawling service
     */
    public int nrOfPagesVisited;

    /**
     *  the number of unique pages visited
     */
    public int nrOfUniquePagesVisited;

    /**
     *  the time elapsed of the scraping
     */
    public long timeElapsed;

    /**
     * the depth of the scraping
     */
    public int depth;

    public CrawlAction(int nrOfPagesVisited, int nrOfUniquePagesVisited, long timeElapsed, int depth) {
        this.nrOfPagesVisited = nrOfPagesVisited;
        this.nrOfUniquePagesVisited = nrOfUniquePagesVisited;
        this.timeElapsed = timeElapsed;
        this.depth = depth;
    }
}
