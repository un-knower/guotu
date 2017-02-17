package org.bbz.srxk.server;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by liulaoye on 17-2-17.
 */
public class DefaultServer implements IServer{

    private static final Logger LOG = LoggerFactory.getLogger( DefaultServer.class );

    /**
     * 最长的包长度
     */
    private static final int MAX_FRAME_SIZE_DEFAULT = 2048;

    /**
     * 缺省的监听端口
     */
    public static final int PORT = 8080;

    /**
     * 缺省的服务器名字
     */

    /**
     * The alias or pseudonym for this proxy, used when adding the Via header.
     */
    private final String proxyAlias;

    /**
     * True when the server has already been stopped by calling {@link #stop()} or {@link #abort()}.
     */
    private final AtomicBoolean stopped = new AtomicBoolean( false );

    private static final String FALLBACK_SERVER_ALIAS = "GUOTU server";


    /**
     * Keep track of all channels created by this  server for later shutdown when the proxy is stopped.
     */
    private final ChannelGroup allChannels = new DefaultChannelGroup( "GUOTU-Server", GlobalEventExecutor.INSTANCE );
    private final Thread jvmShutdownHook = new Thread( new Runnable(){

        @Override
        public void run(){
            abort();
        }
    }, "GUOTO-SERVER-JVM-shutdown-hook" );


    public int getIdleConnectionTimeout(){
        return 0;
    }

    public void setIdleConnectionTimeout( int idleConnectionTimeout ){

    }

    public int getConnectTimeout(){
        return 0;
    }

    public void setConnectTimeout( int connectTimeoutMs ){

    }

    public void stop(){

    }

    public void abort(){

    }

    public InetSocketAddress getListenAddress(){
        return null;
    }

    public void setThrottle( long readThrottleBytesPerSecond, long writeThrottleBytesPerSecond ){

    }

    public static IServerBootstrap bootstrapFromFile( String path ){
        final File propsFile = new File( path );
        Properties props = new Properties();

        if( propsFile.isFile() ) {
            try( InputStream is = new FileInputStream( propsFile ) ) {
                props.load( is );
            } catch( final IOException e ) {
                LOG.warn( "Could not load props file?", e );
            }
        }

        return new ServerBootstrap( props );
//        return new ServerBootstrap();
    }
}