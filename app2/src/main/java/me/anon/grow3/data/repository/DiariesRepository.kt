package me.anon.grow3.data.repository

import androidx.lifecycle.LiveData
import com.zhuinden.eventemitter.EventSource
import me.anon.grow3.data.model.Diary
import me.anon.grow3.data.model.Log
import me.anon.grow3.data.repository.impl.LogEvent
import me.anon.grow3.util.states.DataResult

interface DiariesRepository
{
	public fun observeDiaries(): LiveData<DataResult<List<Diary>>>

	public fun observeDiary(diaryId: String): LiveData<DataResult<Diary>>

	public fun observeLogEvents(): EventSource<LogEvent>

	public suspend fun getDiaries(): List<Diary>

	public suspend fun getDiaryById(diaryId: String): Diary?

	public suspend fun createDiary(diary: Diary): Diary
	public suspend fun deleteDiary(diaryId: String): Boolean

	public suspend fun addLog(log: Log, diary: Diary): Log
	public suspend fun draftLog(log: Log): Log
	public suspend fun getDraftLog(logId: String): Log?

	public fun sync()

	public fun invalidate()
}
