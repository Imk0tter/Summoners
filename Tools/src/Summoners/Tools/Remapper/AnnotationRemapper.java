package Summoners.Tools.Remapper;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

public class AnnotationRemapper extends AnnotationVisitor {
    protected final Remapper remapper;

    public AnnotationRemapper(AnnotationVisitor annotationVisitor, Remapper remapper) {
        this(Opcodes.ASM9, annotationVisitor, remapper);
    }

    protected AnnotationRemapper(int api, AnnotationVisitor annotationVisitor, Remapper remapper) {
        super(api, annotationVisitor);
        this.remapper = remapper;
    }

    public void visit(String name, Object value) {
        super.visit(name, this.remapper.mapValue(value));
    }

    public void visitEnum(String name, String descriptor, String value) {
        super.visitEnum(name, this.remapper.mapDesc(descriptor), value);
    }

    public AnnotationVisitor visitAnnotation(String name, String descriptor) {
        AnnotationVisitor annotationVisitor = super.visitAnnotation(name, this.remapper.mapDesc(descriptor));
        if (annotationVisitor == null) {
            return null;
        } else {
            return (AnnotationVisitor)(annotationVisitor == this.av ? this : this.createAnnotationRemapper(annotationVisitor));
        }
    }

    public AnnotationVisitor visitArray(String name) {
        AnnotationVisitor annotationVisitor = super.visitArray(name);
        if (annotationVisitor == null) {
            return null;
        } else {
            return (AnnotationVisitor)(annotationVisitor == this.av ? this : this.createAnnotationRemapper(annotationVisitor));
        }
    }

    protected AnnotationVisitor createAnnotationRemapper(AnnotationVisitor annotationVisitor) {
        return new AnnotationRemapper(this.api, annotationVisitor, this.remapper);
    }
}
