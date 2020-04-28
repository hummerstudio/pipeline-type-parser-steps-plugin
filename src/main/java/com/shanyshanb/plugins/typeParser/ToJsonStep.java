/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Tang Ming
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.shanyshanb.plugins.typeParser;

import com.google.common.collect.ImmutableSet;
import hudson.Extension;
import hudson.FilePath;
import hudson.model.TaskListener;
import net.sf.json.JSON;
import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Parse a {@link JSON} object to JSON String.
 *
 * @author Tang Ming
 */
public class ToJsonStep extends Step {
    private final Object map;
    private int pretty = 0;

    @DataBoundConstructor
    public ToJsonStep(Object map) {
        this.map = map;
    }

    /**
     * Return the JSON object.
     *
     * <p>
     * If it is not a {@link JSON} object, {@link net.sf.json.JSONObject#fromObject(Object)} will be used in a first
     * step.
     * </p>
     *
     * @return an object
     */
    public Object getMap() {
        return map;
    }

    /**
     * Return the number of spaces used to prettify the JSON String.
     *
     * @return a int
     */
    public int getPretty() {
        return pretty;
    }

    /**
     * Indents to use if the JSON should be pretty printed.
     * A greater than zero integer will do so.
     *
     * @param pretty the indent size
     */
    @DataBoundSetter
    void setPretty(int pretty) {
        this.pretty = pretty;
    }

    @Override
    public StepExecution start(StepContext context) {
        return new ToJsonStepExecution(this, context);
    }

    @Extension
    public static class DescriptorImpl extends StepDescriptor {
        public DescriptorImpl() {
        }

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return ImmutableSet.of(TaskListener.class, FilePath.class);
        }

        @Override
        public String getFunctionName() {
            return "toJson";
        }

        @Override
        @Nonnull
        public String getDisplayName() {
            return Messages.ToJSONStep_DescriptorImpl_displayName();
        }

    }

}