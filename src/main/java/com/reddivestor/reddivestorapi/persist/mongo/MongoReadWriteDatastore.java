package com.reddivestor.reddivestorapi.persist.mongo;

import com.reddivestor.reddivestorapi.models.Crypto;
import com.reddivestor.reddivestorapi.persist.ReadWriteDatastore;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@RequiredArgsConstructor
public class MongoReadWriteDatastore implements ReadWriteDatastore<Crypto>{
    final MongoTemplate mongoTemplate;
    static String CRYPTO_COUNTS_COLLECTION_NAME = "crypto_counts";

    public List<Crypto> findByTimestampBetween(Date lowerBound, Date upperBound){
        List<Crypto> result = new ArrayList<>();

        MatchOperation matchOperation = match(Criteria.where("timestamp").gte(lowerBound).lt(upperBound));
        GroupOperation sumNames = group("name").count().as("coinFrequency");
        SortOperation sortByCount = sort(Sort.Direction.DESC, "coinFrequency");
        Aggregation aggregation = newAggregation(matchOperation, sumNames, sortByCount);
        AggregationResults<Document> mongoResponse = mongoTemplate.aggregate(aggregation, CRYPTO_COUNTS_COLLECTION_NAME, Document.class);

        int index = 1;
        for(int i = 0; i < mongoResponse.getMappedResults().size(); i++) {
            Document current = mongoResponse.getMappedResults().get(i);
            result.add(new Crypto((String) current.get("_id"), (int) current.get("coinFrequency"), index));
            index++;
        }
        return result;
    }
}