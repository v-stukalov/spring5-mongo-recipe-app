package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("Foo");

        unitOfMeasureReactiveRepository.save(uom).block();

        Long count = unitOfMeasureReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void findByDescription() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription("Foo");

        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure fetchedUom = unitOfMeasureReactiveRepository.findByDescription("Foo").block();
        assertNotNull(fetchedUom.getId());
    }
}