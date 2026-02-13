package fr.paris.lutece.plugins.announce.modules.subscribe.business;


import fr.paris.lutece.plugins.subscribe.business.SubscriptionFilter;
import fr.paris.lutece.portal.service.plugin.Plugin;

import java.util.Collection;
import java.util.List;

public interface IAnnounceSubscriptionDAO 
{
    /**
     * Insert a new record in the table.
     * @param subscription instance of the Subscription object to insert
     * @param plugin the Plugin
     */
    void insert(AnnounceSubscriptionDTO subscription, Plugin plugin );

    /**
     * Update the record in the table
     * @param subscription the reference of the Subscription
     * @param plugin the Plugin
     */
    void store(AnnounceSubscriptionDTO subscription, Plugin plugin );

    /**
     * Delete a record from the table
     * @param nIdSubscription int identifier of the Subscription to delete
     * @param plugin the Plugin
     */
    void delete( int nIdSubscription, Plugin plugin );

    ///////////////////////////////////////////////////////////////////////////
    // Finders

    /**
     * Load the data from the table
     * @param nKey The identifier of the subscription
     * @param plugin the plugin
     * @return The instance of the subscription
     */
    AnnounceSubscriptionDTO load(int nKey, Plugin plugin );

    /**
     * Load the data of all the subscription objects and returns them as a
     * collection
     * @param plugin the plugin
     * @return The collection which contains the data of all the subscription
     *         objects
     */
    Collection<AnnounceSubscriptionDTO> selectSubscriptionsList(Plugin plugin );

    /**
     * Find a list of subscriptions from a filter
     * @param filter The filter
     * @param plugin the Plugin
     * @return The list of subscriptions that matches the given filter
     */
    List<AnnounceSubscriptionDTO> findByFilter(SubscriptionFilter filter, Plugin plugin );

    /**
     * Find all subscriptions to a given category
     * @param strProviderName The subscription provider name
     * @param nCategoryId The category id
     * @param plugin the Plugin
     * @return The list of subscriptions for this category
     */
    List<AnnounceSubscriptionDTO> findByCategoryId( String strProviderName, int nCategoryId, Plugin plugin );

    /**
     * Find all subscriptions to a given user
     * @param strProviderName The subscription provider name
     * @param strUserName The user name to look up
     * @param plugin the Plugin
     * @return The list of subscriptions for this user
     */
    List<AnnounceSubscriptionDTO> findByUserName( String strProviderName, String strUserName, Plugin plugin );

    /**
     * Find all filter subscriptions
     * @param strProviderName The subscription provider name
     * @param plugin the Plugin
     * @return The list of all filter subscriptions
     */
    List<AnnounceSubscriptionDTO> findAllFilterSubscriptions( String strProviderName, Plugin plugin );

}
