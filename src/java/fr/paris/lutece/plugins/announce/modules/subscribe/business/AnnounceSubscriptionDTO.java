package fr.paris.lutece.plugins.announce.modules.subscribe.business;

import fr.paris.lutece.plugins.subscribe.business.Subscription;

public class AnnounceSubscriptionDTO extends Subscription 
{

    String _strEmailSubscribes;

    public AnnounceSubscriptionDTO( )
    {
    }

    public AnnounceSubscriptionDTO( String _strEmailSubscribes )
    {
        super();
        this._strEmailSubscribes = _strEmailSubscribes;
    }

    public String getEmailSubscribes() 
    {
        return _strEmailSubscribes;
    }

    public void setEmailSubscribes( String _strEmailSubscribes ) 
    {
        this._strEmailSubscribes = _strEmailSubscribes;
    }

}
