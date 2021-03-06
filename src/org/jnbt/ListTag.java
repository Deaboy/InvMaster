package org.jnbt;

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

import java.lang.reflect.Type;
import java.lang.Class;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.server.v1_7_R1.NBTTagList;

/**
 * The <code>TAG_List</code> tag.
 * @author Graham Edgecombe
 *
 */
public final class ListTag extends Tag {

	/**
	 * The type.
	 */
	private final Class<? extends Tag> type;
	
	/**
	 * The value.
	 */
	private final List<Tag> value;
	
	/**
	 * Creates the tag.
	 * @param type The type of item in the list.
	 * @param value The value.
	 */
	public ListTag(Class<? extends Tag> type, List<Tag> value) {
		this.type = type;
		this.value = Collections.unmodifiableList(value);
	}
	
	/**
	 * Gets the type of item in this list.
	 * @return The type of item in this list.
	 */
	public Class<? extends Tag> getListType() {
		if (type.getName().equals("java.lang.Class"))
			return CompoundTag.class;
		else
			return type;
	}
	
	@Override
	public List<Tag> getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		StringBuilder bldr = new StringBuilder();
		bldr.append("TAG_List: " + value.size() + " entries of type " + NBTUtils.getTypeName(type) + "\r\n{\r\n");
		for(Tag t : value) {
			bldr.append("   " + t.toString().replaceAll("\r\n", "\r\n   ") + "\r\n");
		}
		bldr.append("}");
		return bldr.toString();
	}

	@Override
	public NBTTagList toNBTTag()
	{
		NBTTagList tag = new NBTTagList();
		for (Tag t : this.getValue())
		{
			tag.add(t.toNBTTag());
		}
		return tag;
	}
	
	@SuppressWarnings("unchecked")
	public static ListTag fromNBTTag(NBTTagList base)
	{
		Type type = Tag.fromNBTTag(base.get(0)).getClass();
		List<Tag> list = new ArrayList<>();
		
		for (int i = 0; i < base.size(); i++)
		{
			list.add(Tag.fromNBTTag(base.get(i)));
		}
		return new ListTag((Class<? extends Tag>) type.getClass(), list);
	}

	
	@Override
	public TagType getTagType() {
		return TagType.LIST;
	}

	@Override
	public Type getDataType() {
		return List.class;
	}

}
