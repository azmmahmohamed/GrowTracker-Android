package me.anon.lib.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

public fun merge(vararg liveData: LiveData<*>): LiveData<ArrayList<*>>
{
	return arrayListOf(*liveData).zipMapLiveData()
}

/**
 * // TODO: Add class description
 */
public fun <J> ArrayList<LiveData<J>>.zipLiveData(): LiveData<ArrayList<J>>
{
	return MediatorLiveData<ArrayList<J>>().apply {
		val zippedObjects = ArrayList<J>()
		this@zipLiveData.forEach {
			addSource(it) { item ->
				synchronized(zippedObjects)
				{
					zippedObjects.add(item)

					if (zippedObjects.size == this@zipLiveData.size)
					{
						value = zippedObjects
					}
				}
			}
		}
	}
}

public fun List<LiveData<*>>.zipMapLiveData(): LiveData<ArrayList<*>>
{
	return MediatorLiveData<ArrayList<*>>().apply {
		val zippedObjects = ArrayList<Any>()
		this@zipMapLiveData.forEach {
			addSource(it) { item ->
				if (!zippedObjects.contains(item))
				{
					zippedObjects.add(item)
				}

				if (zippedObjects.size == this@zipMapLiveData.size)
				{
					value = zippedObjects
				}
			}
		}
	}
}