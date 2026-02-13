package fr.paris.lutece.plugins.announce.modules.subscribe.service;

import fr.paris.lutece.plugins.announce.modules.subscribe.business.AnnounceSubscriptionDTO;
import fr.paris.lutece.plugins.announce.modules.subscribe.business.IAnnounceSubscriptionDAO;
import fr.paris.lutece.plugins.subscribe.business.Subscription;
import fr.paris.lutece.plugins.subscribe.business.SubscriptionFilter;
import fr.paris.lutece.plugins.subscribe.service.ISubscriptionProviderService;
import fr.paris.lutece.portal.service.security.LuteceUser;
import fr.paris.lutece.portal.service.security.LuteceUserService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnnounceSubscriptionService
{

	private static final String BEAN_DAO = "subscribe.announceSubscriptionDAO";
	private static AnnounceSubscriptionService _instance = new AnnounceSubscriptionService( );
	private IAnnounceSubscriptionDAO _dao;

	/**
	 * Private constructor
	 */
	private AnnounceSubscriptionService( )
	{
		// Do nothing
	}

	public static AnnounceSubscriptionService getInstance( )
	{
		return _instance;
	}

	private IAnnounceSubscriptionDAO getDAO( )
	{
		if( _dao == null )
		{
			_dao = SpringContextService.getBean( BEAN_DAO );
		}
		return _dao;
	}

	public void createSubscription( AnnounceSubscriptionDTO subscription, LuteceUser user )
	{
		createSubscription( subscription, user.getName( ) );
	}

	public void createSubscription( AnnounceSubscriptionDTO subscription, String strLuteceUserName )
	{
		subscription.setUserId( strLuteceUserName );
		createSubscription( subscription );
	}

	public void createSubscription( AnnounceSubscriptionDTO subscription )
	{
		getDAO( ).insert( subscription, AnnounceSubscribePlugin.getPlugin( ) );
	}

	public Subscription findBySubscriptionId( int nIdSubscription )
	{
		return getDAO( ).load( nIdSubscription, AnnounceSubscribePlugin.getPlugin( ) );
	}

	public List < AnnounceSubscriptionDTO > findByFilter( SubscriptionFilter filter )
	{
		return getDAO( ).findByFilter( filter, AnnounceSubscribePlugin.getPlugin( ) );
	}

	public List < AnnounceSubscriptionDTO > findByCategoryId( String strProviderName, int nCategoryId )
	{
		return getDAO( ).findByCategoryId( strProviderName, nCategoryId, AnnounceSubscribePlugin.getPlugin( ) );
	}

	public List < AnnounceSubscriptionDTO > findByUserName( String strProviderName, String strUserName )
	{
		return getDAO( ).findByUserName( strProviderName, strUserName, AnnounceSubscribePlugin.getPlugin( ) );
	}

	public List < AnnounceSubscriptionDTO > findAllFilterSubscriptions( String strProviderName )
	{
		return getDAO( ).findAllFilterSubscriptions( strProviderName, AnnounceSubscribePlugin.getPlugin( ) );
	}

	public void removeSubscription( int nIdSubscription, boolean bNotifySubscriptionProvider )
	{
		if( bNotifySubscriptionProvider )
		{
			removeSubscription( findBySubscriptionId( nIdSubscription ), bNotifySubscriptionProvider );
		}
		else
		{
			getDAO( ).delete( nIdSubscription, AnnounceSubscribePlugin.getPlugin( ) );
		}
	}

	public void removeSubscription( Subscription subscription, boolean bNotifySubscriptionProvider )
	{
		if( bNotifySubscriptionProvider )
		{
			List < ISubscriptionProviderService > listProviders = SpringContextService
					.getBeansOfType( ISubscriptionProviderService.class );
			for( ISubscriptionProviderService provider : listProviders )
			{
				if( StringUtils.equals( subscription.getSubscriptionProvider( ), provider.getProviderName( ) ) )
				{
					provider.notifySubscriptionRemoval( subscription );
				}
			}
		}
		getDAO( ).delete( subscription.getIdSubscription( ), AnnounceSubscribePlugin.getPlugin( ) );
	}

	public LuteceUser getLuteceUserFromSubscription( Subscription subscription )
	{
		return LuteceUserService.getLuteceUserFromName( subscription.getUserId( ) );
	}

	public Collection < LuteceUser > getSubscriberList( String strSubscriptionProvider, String strSubscriptionKey,
			String strIdSubscribedResource )
	{
		SubscriptionFilter filter = new SubscriptionFilter( );
		filter.setSubscriptionProvider( strSubscriptionProvider );
		filter.setSubscriptionKey( strSubscriptionKey );
		filter.setIdSubscribedResource( strIdSubscribedResource );
		List < AnnounceSubscriptionDTO > listSubscription = findByFilter( filter );
		Set < LuteceUser > usersFound = new HashSet < LuteceUser >( );
		for( Subscription subscription : listSubscription )
		{
			LuteceUser user = LuteceUserService.getLuteceUserFromName( subscription.getUserId( ) );
			if( user != null )
			{
				usersFound.add( user );
			}
		}
		return usersFound;
	}

}
