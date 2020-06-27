package org.yefei.qa.mock.grpc.idl;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: crmOrderService.proto")
public class QaDemoServiceGrpc {

  private QaDemoServiceGrpc() {}

  public static final String SERVICE_NAME = "proto.msg.QaDemoService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.yefei.qa.mock.grpc.idl.TPRequest,
      org.yefei.qa.mock.grpc.idl.TPInfoReply> METHOD_QUERY_TPSERVICE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "proto.msg.QaDemoService", "QueryTPService"),
          io.grpc.protobuf.ProtoUtils.marshaller(org.yefei.qa.mock.grpc.idl.TPRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(org.yefei.qa.mock.grpc.idl.TPInfoReply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QaDemoServiceStub newStub(io.grpc.Channel channel) {
    return new QaDemoServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QaDemoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QaDemoServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static QaDemoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QaDemoServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class QaDemoServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void queryTPService(org.yefei.qa.mock.grpc.idl.TPRequest request,
        io.grpc.stub.StreamObserver<org.yefei.qa.mock.grpc.idl.TPInfoReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_QUERY_TPSERVICE, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_QUERY_TPSERVICE,
            asyncUnaryCall(
              new MethodHandlers<
                org.yefei.qa.mock.grpc.idl.TPRequest,
                org.yefei.qa.mock.grpc.idl.TPInfoReply>(
                  this, METHODID_QUERY_TPSERVICE)))
          .build();
    }
  }

  /**
   */
  public static final class QaDemoServiceStub extends io.grpc.stub.AbstractStub<QaDemoServiceStub> {
    private QaDemoServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QaDemoServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QaDemoServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QaDemoServiceStub(channel, callOptions);
    }

    /**
     */
    public void queryTPService(org.yefei.qa.mock.grpc.idl.TPRequest request,
        io.grpc.stub.StreamObserver<org.yefei.qa.mock.grpc.idl.TPInfoReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_QUERY_TPSERVICE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class QaDemoServiceBlockingStub extends io.grpc.stub.AbstractStub<QaDemoServiceBlockingStub> {
    private QaDemoServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QaDemoServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QaDemoServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QaDemoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.yefei.qa.mock.grpc.idl.TPInfoReply queryTPService(org.yefei.qa.mock.grpc.idl.TPRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_QUERY_TPSERVICE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class QaDemoServiceFutureStub extends io.grpc.stub.AbstractStub<QaDemoServiceFutureStub> {
    private QaDemoServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QaDemoServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QaDemoServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QaDemoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.yefei.qa.mock.grpc.idl.TPInfoReply> queryTPService(
        org.yefei.qa.mock.grpc.idl.TPRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_QUERY_TPSERVICE, getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY_TPSERVICE = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QaDemoServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(QaDemoServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY_TPSERVICE:
          serviceImpl.queryTPService((org.yefei.qa.mock.grpc.idl.TPRequest) request,
              (io.grpc.stub.StreamObserver<org.yefei.qa.mock.grpc.idl.TPInfoReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_QUERY_TPSERVICE);
  }

}
