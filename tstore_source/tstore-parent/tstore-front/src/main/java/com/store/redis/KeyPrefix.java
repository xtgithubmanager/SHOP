package com.store.redis;

public interface KeyPrefix {
	public int expireSeconds();

	public String getPrefix();
}
