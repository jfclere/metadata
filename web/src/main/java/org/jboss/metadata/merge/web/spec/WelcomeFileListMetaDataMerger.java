/*
 * JBoss, Home of Professional Open Source
 * Copyright 2007, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.metadata.merge.web.spec;

import java.util.ArrayList;
import java.util.List;

import org.jboss.metadata.merge.javaee.support.IdMetaDataImplMerger;
import org.jboss.metadata.web.spec.WelcomeFileListMetaData;

/**
 *
 * @author Scott.Stark@jboss.org
 * @version $Revision: 75470 $
 */
public class WelcomeFileListMetaDataMerger extends IdMetaDataImplMerger {
    public static void augment(WelcomeFileListMetaData dest, WelcomeFileListMetaData webFragmentMetaData, WelcomeFileListMetaData webMetaData,
            boolean resolveConflicts) {
        // Note: as this is purely additive, webMetaData is useless
        if (dest.getWelcomeFiles() == null) {
            dest.setWelcomeFiles(webFragmentMetaData.getWelcomeFiles());
        } else if (webFragmentMetaData.getWelcomeFiles() != null) {
            List<String> mergedWelcomeFiles = new ArrayList<String>();
            for (String welcomeFile : dest.getWelcomeFiles()) {
                mergedWelcomeFiles.add(welcomeFile);
            }
            for (String welcomeFile : webFragmentMetaData.getWelcomeFiles()) {
                boolean found = false;
                for (String check : dest.getWelcomeFiles()) {
                    if (check.equals(welcomeFile)) {
                        found = true;
                    }
                }
                if (!found)
                    mergedWelcomeFiles.add(welcomeFile);
            }
            dest.setWelcomeFiles(mergedWelcomeFiles);
        }
    }
}
