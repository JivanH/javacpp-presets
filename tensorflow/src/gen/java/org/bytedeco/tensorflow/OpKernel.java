// Targeted by JavaCPP version 1.5: DO NOT EDIT THIS FILE

package org.bytedeco.tensorflow;

import org.bytedeco.tensorflow.Allocator;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.tensorflow.global.tensorflow.*;


@Namespace("tensorflow") @NoOffset @Properties(inherit = org.bytedeco.tensorflow.presets.tensorflow.class)
public class OpKernel extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public OpKernel(Pointer p) { super(p); }

  // OpKernel won't be instantiated by the scheduler, so you may perform
  // expensive initialization in the descendant's constructor.

  // Specialized constructor that enables the descendant to provide a different
  // `NodeDef` value. For example, this constructor can be used to provide a
  // stripped-down `NodeDef` that does not contain the full set of attrs (such
  // as tensor values) if the descendant stores them in a different form.

  // An OpKernel's computation can be either synchronous or
  // asynchronous. All OpKernel Compute() methods must be thread-safe as they
  // may be called concurrently (e.g. by multiple executions of the same graph
  // concurrently).
  //
  // Most OpKernels should compute synchronously.  They should
  // subclass OpKernel and override the Compute() method and have it
  // return after completing the supplied work.
  //
  // A few special kernels might need to be asynchronous to bound the
  // number of threads (e.g., network receive operations). These
  // kernels must subclass AsyncOpKernel and override
  // AsyncOpKernel::ComputeAsync().
  //
  // In both cases, implementations of Compute() and ComputeAsync()
  // get inputs and write outputs through the given OpKernelContext
  // and returns a status via context->SetStatus(). They must be
  // thread-safe.

  // Synchronous compute.
  //
  // "context" is guaranteed to be alive until Compute() returns.
  public native void Compute(OpKernelContext context);

  // Returns nullptr iff this op kernel is synchronous.
  public native AsyncOpKernel AsAsync();

  // Returns true iff this op kernel is considered "expensive". The
  // runtime may use this flag to optimize graph execution for example
  // to "inline" inexpensive kernels.
  public native @Cast("bool") boolean IsExpensive();

  // Accessors.
  public native @Const @ByRef NodeDef def();
  public native @StdString BytePointer name();              // Same as def().name()
  public native @StdString BytePointer type_string();       // Same as def().op()
  public native @StdString BytePointer requested_device();  // Same as def().device()
  public native @Cast("bool") boolean is_internal();

  public native int num_inputs();
  public native @Cast("tensorflow::DataType") int input_type(int i);
  public native @Const @ByRef DataTypeVector input_types();
  public native @Cast("const tensorflow::MemoryTypeVector*") @ByRef AllocatorAttributesVector input_memory_types();
  public native @StdString BytePointer requested_input(int i);  // Same as def().input(i)

  public native int num_outputs();
  public native @Cast("tensorflow::DataType") int output_type(int o);
  public native @Const @ByRef DataTypeVector output_types();
  public native @Cast("const tensorflow::MemoryTypeVector*") @ByRef AllocatorAttributesVector output_memory_types();

  public native @ByVal Status InputRange(@StringPiece BytePointer input_name, IntPointer start, IntPointer stop);
  public native @ByVal Status InputRange(@StringPiece String input_name, IntBuffer start, IntBuffer stop);
  public native @ByVal Status InputRange(@StringPiece BytePointer input_name, int[] start, int... stop);
  public native @ByVal Status InputRange(@StringPiece String input_name, IntPointer start, IntPointer stop);
  public native @ByVal Status InputRange(@StringPiece BytePointer input_name, IntBuffer start, IntBuffer stop);
  public native @ByVal Status InputRange(@StringPiece String input_name, int[] start, int... stop);
  public native @ByVal Status OutputRange(@StringPiece BytePointer output_name, IntPointer start, IntPointer stop);
  public native @ByVal Status OutputRange(@StringPiece String output_name, IntBuffer start, IntBuffer stop);
  public native @ByVal Status OutputRange(@StringPiece BytePointer output_name, int[] start, int... stop);
  public native @ByVal Status OutputRange(@StringPiece String output_name, IntPointer start, IntPointer stop);
  public native @ByVal Status OutputRange(@StringPiece BytePointer output_name, IntBuffer start, IntBuffer stop);
  public native @ByVal Status OutputRange(@StringPiece String output_name, int[] start, int... stop);

  // We allow legacy scalars within Google up until GraphDef version 6.
  // TODO(irving): Remove when we can drop support for GraphDef version 5.
  public native @Cast("bool") boolean allow_legacy_scalars();

  // Allow either scalars or (if allowing legacy scalars) shape (1,).
  public native @Cast("bool") boolean IsLegacyScalar(@Const @ByRef TensorShape shape);

  // Allow rank 1 or (if allowing legacy scalars) rank 0.
  public native @Cast("bool") boolean IsLegacyVector(@Const @ByRef TensorShape shape);

  // Turn a shape Tensor into a TensorShape
  // TODO(irving): Move to TensorShapeUtils once !allow_legacy_scalars
  public native @ByVal Status MakeShape(@Const @ByRef Tensor shape, TensorShape out);
}
