package model.util.pagination;

/**
 * Helper class to calculate amount of pages
 */
public class PageCalculator {

    /**
     * Accepts
     * @param listSize to
     * @return - needed amount of pages
     *
     */
    public int getNumPages(int listSize) {
        int res = 0;
        if (listSize % 3 == 0) {
            res = listSize / 3;
        } else {
            res = listSize / 3 + 1;
        }
        return res;
    }
}
