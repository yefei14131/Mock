/*
 * Copyright (c) 2010-2017 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package org.apache.skywalking.apm.mock.websocket;

import org.apache.skywalking.apm.mock.websocket.drafts.Draft;
import org.apache.skywalking.apm.mock.websocket.exceptions.InvalidDataException;
import org.apache.skywalking.apm.mock.websocket.exceptions.InvalidHandshakeException;
import org.apache.skywalking.apm.mock.websocket.framing.Framedata;
import org.apache.skywalking.apm.mock.websocket.framing.PingFrame;
import org.apache.skywalking.apm.mock.websocket.framing.PongFrame;
import org.apache.skywalking.apm.mock.websocket.handshake.ClientHandshake;
import org.apache.skywalking.apm.mock.websocket.handshake.HandshakeImpl1Server;
import org.apache.skywalking.apm.mock.websocket.handshake.ServerHandshake;
import org.apache.skywalking.apm.mock.websocket.handshake.ServerHandshakeBuilder;

import java.net.InetSocketAddress;

/**
 * This class default implements all methods of the WebSocketListener that can be overridden optionally when advances functionalities is needed.<br>
 **/
public abstract class WebSocketAdapter implements WebSocketListener {

    /**
     * This default implementation does not do anything. Go ahead and overwrite it.
     *
     * @see org.apache.skywalking.apm.mock.websocket.WebSocketListener#onWebsocketHandshakeReceivedAsServer(WebSocket, Draft, ClientHandshake)
     */
    @Override
    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        return new HandshakeImpl1Server();
    }

    @Override
    public void onWebsocketHandshakeReceivedAsClient(WebSocket conn, ClientHandshake request, ServerHandshake response) throws InvalidDataException {
        //To overwrite
    }

    /**
     * This default implementation does not do anything which will cause the connections to always progress.
     *
     * @see org.apache.skywalking.apm.mock.websocket.WebSocketListener#onWebsocketHandshakeSentAsClient(WebSocket, ClientHandshake)
     */
    @Override
    public void onWebsocketHandshakeSentAsClient(WebSocket conn, ClientHandshake request) throws InvalidDataException {
        //To overwrite
    }

    /**
     * This default implementation does not do anything. Go ahead and overwrite it
     *
     * @see org.apache.skywalking.apm.mock.websocket.WebSocketListener#onWebsocketMessageFragment(WebSocket, Framedata)
     */
    @Override
    @Deprecated
    public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
        //To overwrite
    }

    /**
     * This default implementation will send a pong in response to the received ping.
     * The pong frame will have the same payload as the ping frame.
     *
     * @see org.apache.skywalking.apm.mock.websocket.WebSocketListener#onWebsocketPing(WebSocket, Framedata)
     */
    @Override
    public void onWebsocketPing(WebSocket conn, Framedata f) {
        conn.sendFrame(new PongFrame((PingFrame) f));
    }

    /**
     * This default implementation does not do anything. Go ahead and overwrite it.
     *
     * @see org.apache.skywalking.apm.mock.websocket.WebSocketListener#onWebsocketPong(WebSocket, Framedata)
     */
    @Override
    public void onWebsocketPong(WebSocket conn, Framedata f) {
        //To overwrite
    }

    /**
     * Gets the XML string that should be returned if a client requests a Flash
     * security policy.
     * <p>
     * The default implementation allows access from all remote domains, but
     * only on the port that this WebSocketServer is listening on.
     * <p>
     * This is specifically implemented for gitime's WebSocket client for Flash:
     * http://github.com/gimite/web-socket-js
     *
     * @return An XML String that comforts to Flash's security policy. You MUST
     * not include the null char at the end, it is appended automatically.
     * @throws InvalidDataException thrown when some data that is required to generate the flash-policy like the websocket local port could not be obtained e.g because the websocket is not connected.
     */
    @Override
    public String getFlashPolicy(WebSocket conn) throws InvalidDataException {
        InetSocketAddress adr = conn.getLocalSocketAddress();
        if (null == adr) {
            throw new InvalidHandshakeException("socket not bound");
        }

        return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + adr.getPort() + "\" /></cross-domain-policy>\0";
    }

}
