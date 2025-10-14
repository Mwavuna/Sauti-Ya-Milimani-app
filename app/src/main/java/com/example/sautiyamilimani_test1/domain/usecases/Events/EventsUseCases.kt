package com.example.sautiyamilimani_test1.domain.usecases.Events

data class EventsUseCases(
    val addEventUseCase: AddEventUseCase,
    val editEventUseCase: EditEventUseCase,
    val deleteEventUseCase: DeleteEventUseCase,
    val getEventsUseCase: GetEventsUseCase
)
