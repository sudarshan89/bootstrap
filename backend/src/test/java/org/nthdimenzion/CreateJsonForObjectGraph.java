package org.nthdimenzion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.nthdimenzion.security.domain.SecurityGroup;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 3/8/13
 * Time: 11:38 AM
 */
public class CreateJsonForObjectGraph {

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Hello Object Graph");

        // Put in the class for which you want to generate the object graph
        SecurityGroup result1 = fillDataIntoObjectGraph(SecurityGroup.class);

        ObjectMapper mapper = new ObjectMapper();

        configureMapper(mapper);

        String result = mapper.writeValueAsString(result1);

        System.out.println("\n\n\n" + result);
    }

    private static void configureMapper(ObjectMapper mapper) {
        mapper.registerModule(new JodaModule());
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new
                MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(mapper);
    }

    private static <T> T fillDataIntoObjectGraph(Class<T> clazz){
        PodamFactory factory = new PodamFactoryImpl(); //This will use the default Random Data Provider Strategy
        T objectGraph = factory.manufacturePojo(clazz);
        return objectGraph;

    }
}
