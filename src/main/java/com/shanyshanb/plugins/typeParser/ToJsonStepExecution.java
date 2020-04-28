/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Tang Ming
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

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;

import javax.annotation.Nonnull;

/**
 * Execution of {@link ToJsonStep}.
 *
 * @author Tang Ming
 */
public class ToJsonStepExecution extends SynchronousNonBlockingStepExecution<String> {
    private static final long serialVersionUID = 1L;
    private transient ToJsonStep step;

    protected ToJsonStepExecution(@Nonnull ToJsonStep step, @Nonnull StepContext context) {
        super(context);
        this.step = step;
    }

    @Override
    protected String run() {
        JSON jsonObject;
        if (step.getMap() instanceof JSON) {
            jsonObject = (JSON) step.getMap();
        } else {
            jsonObject = JSONSerializer.toJSON(step.getMap());
        }
        return jsonObject.toString(step.getPretty());
    }
}