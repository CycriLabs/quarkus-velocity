package com.cycrilabs.quarkus.velocity.deployment;

import java.util.TreeMap;

import org.apache.velocity.runtime.directive.ForeachScope;
import org.jboss.jandex.IndexView;

import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.IndexDependencyBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;

public class VelocityProcessor {
    private static final String FEATURE = "quarkus-velocity";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    NativeImageResourceBuildItem initResources() {
        return new NativeImageResourceBuildItem(
                "org/apache/velocity/runtime/defaults/velocity.properties",
                "org/apache/velocity/runtime/defaults/directive.properties");
    }

    @BuildStep
    void reflectiveClass(final BuildProducer<ReflectiveClassBuildItem> reflectiveClass,
            final CombinedIndexBuildItem combinedIndex) {
        final IndexView index = combinedIndex.getIndex();
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(
                                index.getKnownClasses().stream()
                                        .map(ci -> ci.name().toString())
                                        .filter(n -> n.startsWith("org.apache.velocity.runtime") ||
                                                n.startsWith("org.apache.velocity.util.introspection"))
                                        .toArray(String[]::new))
                        .build());
        reflectiveClass.produce(
                ReflectiveClassBuildItem.builder(
                                TreeMap.class.getName(),
                                ForeachScope.class.getName())
                        .methods()
                        .build());
    }

    @BuildStep
    IndexDependencyBuildItem registerDependencyForIndex() {
        return new IndexDependencyBuildItem("org.apache.velocity", "velocity-engine-core");
    }
}
