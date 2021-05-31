package model.util.pagination;

import java.util.List;

/**
 * Helper class for pagination
 * @param <T>
 */
public class Paginator <T>{
    /**
     * Accepts list of entities to get portion for print on page
     * @param entities - entities form DB
     * @param startIndex - index to start copying list
     * @param lastIndex - index to end copying list
     * @return portion of entities to send on page
     */
    public List<T> getEntitiesForPage(List<T> entities, int startIndex, int lastIndex) {
        List<T> sortedCars;
        if(entities.size()-1 < lastIndex){
            lastIndex =startIndex+ ((entities.size())-startIndex);
        }
        sortedCars = entities.subList(startIndex, lastIndex);
        return sortedCars;
    }
}
