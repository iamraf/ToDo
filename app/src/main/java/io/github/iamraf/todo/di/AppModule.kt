package io.github.iamraf.todo.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.iamraf.todo.data.ToDoDataSourceImpl
import io.github.iamraf.todo.data.ToDoRepository
import io.github.iamraf.todo.domain.ToDoDataSource
import io.github.iamraf.todo.framework.ToDoRepositoryImpl
import io.github.iamraf.todo.usecase.FetchToDoUseCase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideToDoRepository(firestore: FirebaseFirestore): ToDoRepository {
        return ToDoRepositoryImpl(firestore)
    }

    @Provides
    fun provideToDoDataSource(repository: ToDoRepository): ToDoDataSource {
        return ToDoDataSourceImpl(repository)
    }

    @Provides
    fun provideFetchToDoUseCase(dataSource: ToDoDataSource): FetchToDoUseCase {
        return FetchToDoUseCase(dataSource)
    }
}