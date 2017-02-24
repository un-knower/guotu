package org.bbz.srxk.guotu.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.bbz.srxk.guotu.handler.codec.MessageContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by liulaoye on 17-2-20.
 * 真正的业务核心处理
 */
public class ProcessHandler extends SimpleChannelInboundHandler<MessageContainer>{
    private static final Logger LOG = LoggerFactory.getLogger( ProcessHandler.class );
    private int logCount;

    @Override
    protected void channelRead0( ChannelHandlerContext ctx, MessageContainer container ) throws Exception{
        int data = container.getData().readInt();

        if( logCount++ % 20 == 0 ) {

            LOG.debug( ctx.channel().remoteAddress() + " : " + container.toString() );
//            LOG.debug( data + "" );
            System.out.println( "\n" );
        }
    }

    @Override
    public void channelActive( ChannelHandlerContext ctx ) throws Exception{
        try {
//            ctx.channel().eventLoop().scheduleAtFixedRate( new Runnable(){
//                private  int count=0;
//
//                @Override
//                public void run(){
//                    ctx.writeAndFlush( "hello "+ctx.channel().remoteAddress()+" the count is " + count++ );
//                }
//            }, 1, 1, TimeUnit.SECONDS );

        } finally {

            super.channelActive( ctx );
        }
    }

}
