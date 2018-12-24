/*
 * This file is part of the Disco Deterministic Network Calculator.
 *
 * Copyright (C) 2017+ The DiscoDNC contributors
 *
 * disco | Distributed Computer Systems Lab
 * University of Kaiserslautern, Germany
 *
 * http://discodnc.cs.uni-kl.de
 *
 *
 * The Disco Deterministic Network Calculator (DiscoDNC) is free software;
 * you can redistribute it and/or modify it under the terms of the 
 * GNU Lesser General Public License as published by the Free Software Foundation; 
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 */

 package de.uni_kl.cs.discodnc;

import java.io.File;

import de.uni_kl.cs.discodnc.AlgDncBackend;
import de.uni_kl.cs.discodnc.algebra.MinPlus;
import de.uni_kl.cs.discodnc.algebra.disco.affine.MinPlus_Disco_Affine;
import de.uni_kl.cs.discodnc.curves.Curve_PwAffine;
import de.uni_kl.cs.discodnc.curves.LinearSegment;
import de.uni_kl.cs.discodnc.curves.mpa_rtc.pw_affine.Curve_MPARTC_PwAffine;
import de.uni_kl.cs.discodnc.curves.mpa_rtc.pw_affine.LinearSegment_MPARTC_PwAffine;

public enum AlgDncBackend_MPARTC_DISCO_Affine implements AlgDncBackend {
	MPARTC_PWAFFINEC_DISCO_AFFINEMP;

	@Override
	public MinPlus getMinPlus() {
		return MinPlus_Disco_Affine.MINPLUS_DISCO_AFFINE;
	}

	@Override
	public Curve_PwAffine getCurveFactory() {
		return Curve_MPARTC_PwAffine.getFactory();
	}

	@Override 
	public void checkDependencies() {
		String classpath = System.getProperty("java.class.path");
		for (String classpathEntry : classpath.split(File.pathSeparator)) {
			if (classpathEntry.contains("rtc.jar")) {
				return;
			}
		}
		throw new RuntimeException("rtc.jar cannot be found on the classpath!");
	}
	
	@Override
	public LinearSegment.Builder getLinearSegmentFactory() {
		return LinearSegment_MPARTC_PwAffine.getBuilder();
	}

    @Override
    public String toString() {
        return assembleString(this.name(), MinPlus_Disco_Affine.MINPLUS_DISCO_AFFINE.name());
    }
}
