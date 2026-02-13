package fr.paris.lutece.plugins.announce.modules.subscribe.business;

/**
 * Constants for announce subscription keys used in the subscribe_subscription table.
 */
public final class AnnounceSubscriptionKeys
{
	/** Subscription key for subscribing to announces published by a user */
	public static final String SUBSCRIPTION_USER = "announce_user";

	/** Subscription key for subscribing to announces of a given category */
	public static final String SUBSCRIPTION_CATEGORY = "announce_category";

	/** Subscription key for subscribing to announces matching a search filter */
	public static final String SUBSCRIPTION_FILTER = "announce_filter";

	private AnnounceSubscriptionKeys( )
	{
		// Utility class
	}
}
