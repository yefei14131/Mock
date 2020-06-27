// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: crmOrderService.proto

package org.yefei.qa.mock.grpc.idl;

/**
 * Protobuf type {@code proto.msg.TPInfoReply}
 */
public  final class TPInfoReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:proto.msg.TPInfoReply)
    TPInfoReplyOrBuilder {
  // Use TPInfoReply.newBuilder() to construct.
  private TPInfoReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TPInfoReply() {
    orderID_ = 0L;
    orderKey_ = 0L;
    shopOrderKey_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private TPInfoReply(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            orderID_ = input.readInt64();
            break;
          }
          case 16: {

            orderKey_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            shopOrderKey_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.yefei.qa.mock.grpc.idl.CrmOrderService.internal_static_proto_msg_TPInfoReply_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.yefei.qa.mock.grpc.idl.CrmOrderService.internal_static_proto_msg_TPInfoReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.yefei.qa.mock.grpc.idl.TPInfoReply.class, org.yefei.qa.mock.grpc.idl.TPInfoReply.Builder.class);
  }

  public static final int ORDERID_FIELD_NUMBER = 1;
  private long orderID_;
  /**
   * <pre>
   *  orderID
   * </pre>
   *
   * <code>optional int64 orderID = 1;</code>
   */
  public long getOrderID() {
    return orderID_;
  }

  public static final int ORDERKEY_FIELD_NUMBER = 2;
  private long orderKey_;
  /**
   * <code>optional int64 orderKey = 2;</code>
   */
  public long getOrderKey() {
    return orderKey_;
  }

  public static final int SHOPORDERKEY_FIELD_NUMBER = 3;
  private volatile java.lang.Object shopOrderKey_;
  /**
   * <code>optional string shopOrderKey = 3;</code>
   */
  public java.lang.String getShopOrderKey() {
    java.lang.Object ref = shopOrderKey_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      shopOrderKey_ = s;
      return s;
    }
  }
  /**
   * <code>optional string shopOrderKey = 3;</code>
   */
  public com.google.protobuf.ByteString
      getShopOrderKeyBytes() {
    java.lang.Object ref = shopOrderKey_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      shopOrderKey_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (orderID_ != 0L) {
      output.writeInt64(1, orderID_);
    }
    if (orderKey_ != 0L) {
      output.writeInt64(2, orderKey_);
    }
    if (!getShopOrderKeyBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, shopOrderKey_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (orderID_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, orderID_);
    }
    if (orderKey_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, orderKey_);
    }
    if (!getShopOrderKeyBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, shopOrderKey_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.yefei.qa.mock.grpc.idl.TPInfoReply)) {
      return super.equals(obj);
    }
    org.yefei.qa.mock.grpc.idl.TPInfoReply other = (org.yefei.qa.mock.grpc.idl.TPInfoReply) obj;

    boolean result = true;
    result = result && (getOrderID()
        == other.getOrderID());
    result = result && (getOrderKey()
        == other.getOrderKey());
    result = result && getShopOrderKey()
        .equals(other.getShopOrderKey());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + ORDERID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getOrderID());
    hash = (37 * hash) + ORDERKEY_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getOrderKey());
    hash = (37 * hash) + SHOPORDERKEY_FIELD_NUMBER;
    hash = (53 * hash) + getShopOrderKey().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.yefei.qa.mock.grpc.idl.TPInfoReply parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.yefei.qa.mock.grpc.idl.TPInfoReply prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code proto.msg.TPInfoReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:proto.msg.TPInfoReply)
      org.yefei.qa.mock.grpc.idl.TPInfoReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.yefei.qa.mock.grpc.idl.CrmOrderService.internal_static_proto_msg_TPInfoReply_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.yefei.qa.mock.grpc.idl.CrmOrderService.internal_static_proto_msg_TPInfoReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.yefei.qa.mock.grpc.idl.TPInfoReply.class, org.yefei.qa.mock.grpc.idl.TPInfoReply.Builder.class);
    }

    // Construct using org.yefei.qa.mock.grpc.idl.TPInfoReply.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      orderID_ = 0L;

      orderKey_ = 0L;

      shopOrderKey_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.yefei.qa.mock.grpc.idl.CrmOrderService.internal_static_proto_msg_TPInfoReply_descriptor;
    }

    public org.yefei.qa.mock.grpc.idl.TPInfoReply getDefaultInstanceForType() {
      return org.yefei.qa.mock.grpc.idl.TPInfoReply.getDefaultInstance();
    }

    public org.yefei.qa.mock.grpc.idl.TPInfoReply build() {
      org.yefei.qa.mock.grpc.idl.TPInfoReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.yefei.qa.mock.grpc.idl.TPInfoReply buildPartial() {
      org.yefei.qa.mock.grpc.idl.TPInfoReply result = new org.yefei.qa.mock.grpc.idl.TPInfoReply(this);
      result.orderID_ = orderID_;
      result.orderKey_ = orderKey_;
      result.shopOrderKey_ = shopOrderKey_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.yefei.qa.mock.grpc.idl.TPInfoReply) {
        return mergeFrom((org.yefei.qa.mock.grpc.idl.TPInfoReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.yefei.qa.mock.grpc.idl.TPInfoReply other) {
      if (other == org.yefei.qa.mock.grpc.idl.TPInfoReply.getDefaultInstance()) return this;
      if (other.getOrderID() != 0L) {
        setOrderID(other.getOrderID());
      }
      if (other.getOrderKey() != 0L) {
        setOrderKey(other.getOrderKey());
      }
      if (!other.getShopOrderKey().isEmpty()) {
        shopOrderKey_ = other.shopOrderKey_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.yefei.qa.mock.grpc.idl.TPInfoReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.yefei.qa.mock.grpc.idl.TPInfoReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long orderID_ ;
    /**
     * <pre>
     *  orderID
     * </pre>
     *
     * <code>optional int64 orderID = 1;</code>
     */
    public long getOrderID() {
      return orderID_;
    }
    /**
     * <pre>
     *  orderID
     * </pre>
     *
     * <code>optional int64 orderID = 1;</code>
     */
    public Builder setOrderID(long value) {
      
      orderID_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *  orderID
     * </pre>
     *
     * <code>optional int64 orderID = 1;</code>
     */
    public Builder clearOrderID() {
      
      orderID_ = 0L;
      onChanged();
      return this;
    }

    private long orderKey_ ;
    /**
     * <code>optional int64 orderKey = 2;</code>
     */
    public long getOrderKey() {
      return orderKey_;
    }
    /**
     * <code>optional int64 orderKey = 2;</code>
     */
    public Builder setOrderKey(long value) {
      
      orderKey_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int64 orderKey = 2;</code>
     */
    public Builder clearOrderKey() {
      
      orderKey_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object shopOrderKey_ = "";
    /**
     * <code>optional string shopOrderKey = 3;</code>
     */
    public java.lang.String getShopOrderKey() {
      java.lang.Object ref = shopOrderKey_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        shopOrderKey_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string shopOrderKey = 3;</code>
     */
    public com.google.protobuf.ByteString
        getShopOrderKeyBytes() {
      java.lang.Object ref = shopOrderKey_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        shopOrderKey_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string shopOrderKey = 3;</code>
     */
    public Builder setShopOrderKey(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      shopOrderKey_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string shopOrderKey = 3;</code>
     */
    public Builder clearShopOrderKey() {
      
      shopOrderKey_ = getDefaultInstance().getShopOrderKey();
      onChanged();
      return this;
    }
    /**
     * <code>optional string shopOrderKey = 3;</code>
     */
    public Builder setShopOrderKeyBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      shopOrderKey_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:proto.msg.TPInfoReply)
  }

  // @@protoc_insertion_point(class_scope:proto.msg.TPInfoReply)
  private static final org.yefei.qa.mock.grpc.idl.TPInfoReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.yefei.qa.mock.grpc.idl.TPInfoReply();
  }

  public static org.yefei.qa.mock.grpc.idl.TPInfoReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TPInfoReply>
      PARSER = new com.google.protobuf.AbstractParser<TPInfoReply>() {
    public TPInfoReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new TPInfoReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TPInfoReply> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TPInfoReply> getParserForType() {
    return PARSER;
  }

  public org.yefei.qa.mock.grpc.idl.TPInfoReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

