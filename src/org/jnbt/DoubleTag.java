package org.jnbt;

import java.lang.reflect.Type;

import net.minecraft.server.v1_7_R1.NBTTagDouble;

/*
 * JNBT License
 * 
 * Copyright (c) 2010 Graham Edgecombe
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *       
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *       
 *     * Neither the name of the JNBT team nor the names of its
 *       contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE. 
 */

/**
 * The <code>TAG_Double</code> tag.
 * @author Graham Edgecombe
 *
 */
public final class DoubleTag extends Tag {
	
	/**
	 * The value.
	 */
	private final double value;

	/**
	 * Creates the tag.
	 * @param value The value.
	 */
	public DoubleTag(double value) {
		this.value = value;
	}
	
	@Override
	public Double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "TAG_Double: " + value;
	}

	@Override
	public NBTTagDouble toNBTTag()
	{
		return new NBTTagDouble(this.getValue());
	}
	
	public static DoubleTag fromNBTTag(NBTTagDouble base)
	{
		return new DoubleTag(base.g());
	}

	
	@Override
	public TagType getTagType() {
		return TagType.DOUBLE;
	}

	
	@Override
	public Type getDataType() {
		return double.class;
	}
}
