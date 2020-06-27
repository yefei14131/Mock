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
    comments = "Source: MockServerVariableService.proto")
public class VariableServiceGrpc {

  private VariableServiceGrpc() {}

  public static final String SERVICE_NAME = "mock.server.grpc.VariableService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<org.yefei.qa.mock.grpc.idl.VariableDefineRequest,
      org.yefei.qa.mock.grpc.idl.VariableDefineResp> METHOD_PRE_DEFINE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "mock.server.grpc.VariableService", "PreDefine"),
          io.grpc.protobuf.ProtoUtils.marshaller(org.yefei.qa.mock.grpc.idl.VariableDefineRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(org.yefei.qa.mock.grpc.idl.VariableDefineResp.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static VariableServiceStub newStub(io.grpc.Channel channel) {
    return new VariableServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static VariableServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new VariableServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static VariableServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new VariableServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class VariableServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void preDefine(org.yefei.qa.mock.grpc.idl.VariableDefineRequest request,
        io.grpc.stub.StreamObserver<org.yefei.qa.mock.grpc.idl.VariableDefineResp> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_PRE_DEFINE, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_PRE_DEFINE,
            asyncUnaryCall(
              new MethodHandlers<
                org.yefei.qa.mock.grpc.idl.VariableDefineRequest,
                org.yefei.qa.mock.grpc.idl.VariableDefineResp>(
                  this, METHODID_PRE_DEFINE)))
          .build();
    }
  }

  /**
   */
  public static final class VariableServiceStub extends io.grpc.stub.AbstractStub<VariableServiceStub> {
    private VariableServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private VariableServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VariableServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new VariableServiceStub(channel, callOptions);
    }

    /**
     */
    public void preDefine(org.yefei.qa.mock.grpc.idl.VariableDefineRequest request,
        io.grpc.stub.StreamObserver<org.yefei.qa.mock.grpc.idl.VariableDefineResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_PRE_DEFINE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class VariableServiceBlockingStub extends io.grpc.stub.AbstractStub<VariableServiceBlockingStub> {
    private VariableServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private VariableServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VariableServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new VariableServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public org.yefei.qa.mock.grpc.idl.VariableDefineResp preDefine(org.yefei.qa.mock.grpc.idl.VariableDefineRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_PRE_DEFINE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class VariableServiceFutureStub extends io.grpc.stub.AbstractStub<VariableServiceFutureStub> {
    private VariableServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private VariableServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected VariableServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new VariableServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.yefei.qa.mock.grpc.idl.VariableDefineResp> preDefine(
        org.yefei.qa.mock.grpc.idl.VariableDefineRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_PRE_DEFINE, getCallOptions()), request);
    }
  }

  private static final int METHODID_PRE_DEFINE = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final VariableServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(VariableServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PRE_DEFINE:
          serviceImpl.preDefine((org.yefei.qa.mock.grpc.idl.VariableDefineRequest) request,
              (io.grpc.stub.StreamObserver<org.yefei.qa.mock.grpc.idl.VariableDefineResp>) responseObserver);
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
        METHOD_PRE_DEFINE);
  }

}
