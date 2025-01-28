package com.fontys.nameless_backend.Persistence;

import com.fontys.nameless_backend.Business.EventRepository;
import com.fontys.nameless_backend.Domain.event.ItemCount;
import com.fontys.nameless_backend.Domain.event.MainEvent;
import com.fontys.nameless_backend.Persistence.entities.event.ItemCountEntity;
import com.fontys.nameless_backend.Persistence.entities.event.MainEventEntity;
import com.fontys.nameless_backend.Persistence.entities.event.CountEntity;
import com.fontys.nameless_backend.Persistence.mongoDb.MainEventEntityRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Primary
@Repository
@AllArgsConstructor
public class EventRepositoryImpl implements EventRepository {
    private MainEventEntityRepository mainEventEntityRepository;
    private ModelMapper modelMapper;
    private MongoTemplate mongoTemplate;

    @Override
    public void AddEvent(MainEvent event) {
        mainEventEntityRepository.save(convertToEntity(event));
    }

    @Override
    public List<MainEvent> GetAllEvents() {
        return convertToDomainList(mainEventEntityRepository.findAll());
    }

    @Override
    public List<ItemCount> getBrowserDistribution() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("eventPayload.context.client.browser.name")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    @Override
    public List<ItemCount> getLanguageDistribution() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("eventPayload.context.client.browser.language")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    @Override
    public List<ItemCount> GetOperatingSystemDistribution() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("eventPayload.context.client.operatingSystem.name")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    @Override
    public List<ItemCount> GetCountryDistribution() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("eventPayload.context.client.geolocation.country")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    @Override
    public List<ItemCount> GetItemCountCategories() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("eventPayload.event.data.items", true),
                Aggregation.group("eventPayload.event.data.items.item_category")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    @Override
    public List<ItemCount> GetDevicesCount() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("eventPayload.context.client.device.type")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    @Override
    public List<ItemCount> GetMarketingSourceCount() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("eventPayload.context.session.utmParameters.source")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    @Override
    public CountEntity GetSessionsCount(int days) {
        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(days);
        String thresholdISODate = thresholdDate.format(DateTimeFormatter.ISO_DATE_TIME);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("eventHeaders.eventTimestamp")
                        .gte(thresholdISODate)),

                Aggregation.group("eventPayload.context.session.buttSessionId")
                        .max("eventHeaders.eventTimestamp").as("latestTimestamp"),

                Aggregation.project("latestTimestamp")
                        .and("_id").as("sessionId")
                        .andExclude("_id"),
                Aggregation.count().as("count")
        );

        AggregationResults<CountEntity> results = mongoTemplate.aggregate(aggregation, "mainEventCollection", CountEntity.class);

        CountEntity result = results.getUniqueMappedResult();
        return result != null ? result : new CountEntity(0);
    }

    @Override
    public CountEntity GetEventCount() {
        return null;
    }

    @Override
    public List<ItemCount> getTotalEventsByDays(int daysAgo) {
        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(daysAgo);
        String thresholdISODate = thresholdDate.format(DateTimeFormatter.ISO_DATE_TIME);

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("eventHeaders.eventTimestamp")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)
                        .gte(thresholdISODate)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }


    @Override
    public List<ItemCount> getItemCountTrucks() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("eventPayload.event.data.items", true),
                Aggregation.group("eventPayload.event.data.items.item_name")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    @Override
    public List<ItemCount> getItemCountBrands() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("eventPayload.event.data.items", true),
                Aggregation.group("eventPayload.event.data.items.item_brand")
                        .count().as("count"),
                Aggregation.match(Criteria.where("_id").ne(null)),
                Aggregation.project("count")
                        .and("_id").as("item")
                        .andExclude("_id")
        );

        AggregationResults<ItemCountEntity> results = mongoTemplate.aggregate(aggregation, MainEventEntity.class, ItemCountEntity.class);
        return convertItemNameToDomainList(results.getMappedResults());
    }

    private MainEventEntity convertToEntity(MainEvent event) {
        return modelMapper.map(event, MainEventEntity.class);
    }

    private MainEvent convertToDomain(MainEventEntity eventEntity) {
        return modelMapper.map(eventEntity, MainEvent.class);
    }

    private List<MainEvent> convertToDomainList(List<MainEventEntity> eventEntities) {
        return eventEntities.stream()
                .map(entity -> modelMapper.map(entity, MainEvent.class))
                .collect(Collectors.toList());
    }

    private List<ItemCount> convertItemNameToDomainList(List<ItemCountEntity> itemNameCountEntities) {
        return itemNameCountEntities.stream()
                .map(entity -> modelMapper.map(entity, ItemCount.class))
                .collect(Collectors.toList());
    }
}
