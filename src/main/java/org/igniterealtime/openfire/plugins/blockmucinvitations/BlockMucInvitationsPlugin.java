package org.igniterealtime.openfire.plugins.blockmucinvitations;

import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.openfire.interceptor.InterceptorManager;

import java.io.File;

public class BlockMucInvitationsPlugin implements Plugin
{
    private MucInvitationInterceptor mucInvitationInterceptor;
    @Override
    public void initializePlugin(PluginManager pluginManager, File file)
    {
        mucInvitationInterceptor = new MucInvitationInterceptor();
        InterceptorManager.getInstance().addInterceptor(mucInvitationInterceptor);
    }

    @Override
    public void destroyPlugin()
    {
        InterceptorManager.getInstance().removeInterceptor(mucInvitationInterceptor);
    }
}
