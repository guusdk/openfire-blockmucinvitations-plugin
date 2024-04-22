package org.igniterealtime.openfire.plugins.blockmucinvitations;

import org.dom4j.Element;
import org.dom4j.QName;
import org.jivesoftware.openfire.interceptor.PacketInterceptor;
import org.jivesoftware.openfire.interceptor.PacketRejectedException;
import org.jivesoftware.openfire.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmpp.packet.Packet;

public class MucInvitationInterceptor implements PacketInterceptor
{
    private static final Logger Log = LoggerFactory.getLogger(MucInvitationInterceptor.class);

    @Override
    public void interceptPacket(Packet packet, Session session, boolean incoming, boolean processed) throws PacketRejectedException
    {
        if (processed) {
            return;
        }

        final Element extension = packet.getElement().element(QName.get("x", "http://jabber.org/protocol/muc#user"));
        if (extension == null) {
            return;
        }

        final Element invite = extension.element("invite");
        if (invite != null) {
            Log.info("Rejecting MUC invite: {}", packet.toXML());
            throw new PacketRejectedException("Rejected MUC invite.");
        }
    }
}
