package org.glamey.training.jvm.ast;

import com.google.common.collect.Sets;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * http://www.moniaa.cn/blog/2019/01/15/how-to-write-code-generator/
 * <p>
 * {@link SupportedSourceVersion} {@link SupportedAnnotationTypes}两个注解相等于重写的
 * {@link AbstractProcessor#getSupportedSourceVersion()}和 {@link AbstractProcessor#getSupportedAnnotationTypes()}
 * <p>
 * 两者保留一个即可
 *
 * @author yang.zhou 2020.01.14.22
 */
//@SupportedAnnotationTypes({"org.glamey.training.jvm.annotionProcessing.DemoAnnotation"})
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class BuilderProcessor extends AbstractProcessor {

    private static final String LINE = System.getProperty("line.separator");
    private Messager messager;
    private Elements elementUtils;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.filer = processingEnv.getFiler();
        this.elementUtils = processingEnv.getElementUtils();
        messager.printMessage(Diagnostic.Kind.NOTE, "init");
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        System.out.println("--> getSupportedSourceVersion");
        return SourceVersion.latestSupported();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        messager.printMessage(Diagnostic.Kind.NOTE, "getSupportedAnnotationTypes");
        Set<String> annotationTypes = Sets.newHashSet(
                Builder.class.getCanonicalName()
        );
        return annotationTypes;
    }

    /**
     * 按照轮次遍历所有源代码中符合条件的类
     *
     * @param annotations 支持的注解类
     * @param roundEnv    每个轮次中携带的环境相关信息
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "process");

        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(Builder.class);

        elementsAnnotatedWith.stream()
                .filter(element -> element.getKind() == ElementKind.CLASS)
                .forEach(element -> {
                    TypeElement typeElement = (TypeElement) element;
                    PackageElement packageElement = elementUtils.getPackageOf(typeElement);
                    String packagePath = packageElement.getQualifiedName().toString();
                    String className = typeElement.getSimpleName().toString();

                    try {
                        JavaFileObject sourceFile = filer.createSourceFile(packagePath + "." + className + "_xx", typeElement);
                        Writer writer = sourceFile.openWriter();
                        writer.write(generateSource(packagePath, className));
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        return false;
    }

    private String generateSource(String packagePath, String className) {
        StringBuilder builder = new StringBuilder();
        builder.append("package ").append(packagePath).append(";").append(LINE);
        builder.append("import ").append(packagePath).append(".").append(className).append(";").append(LINE);
        builder.append(LINE);
        builder.append("public class ").append(className).append("_xx").append(" {").append(LINE);

        builder.append("    public ").append(className).append(" ").append(" proxy;").append(LINE);

        builder.append("}").append(LINE);
        return "0";
    }
}
