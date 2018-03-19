package com.github.study.dubbo.serialize;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.fasterxml.jackson.module.scala.DefaultScalaModule;
import com.github.study.dubbo.serialize.domain.Person;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.commons.lang3.StringUtils;
import org.msgpack.MessagePack;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 */
public class SerializerBenchmark {
    private static final int WARMUP_COUNT = 100;
    private static final int TEST_COUNT = 1000 * 1000;

    /** Column index */
    private static final int COL_SER_SIZE = 0;
    private static final int COL_SER_COST = 1;
    private static final int COL_DER_COST = 2;
    private static final char[] ALPHA =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


    public static void main(String[] args) throws Exception {

        Serializer[] serializers = {
                        // ============= JSON ==============
                        new Serializer<Person>() {
                            private ObjectMapper mapper = new ObjectMapper();

                            @Override
                            public String name() {
                                return "Jackson";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                return mapper.writeValueAsBytes(obj);
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                return mapper.readValue(data, type);
                            }
                        },
                        new Serializer<Person>() {
                            private Gson gson = new GsonBuilder().create();

                            @Override
                            public String name() {
                                return "Gson";
                            }

                            @Override
                            public byte[] serialize(Person obj) {
                                return gson.toJson(obj).getBytes();
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) {
                                return gson.fromJson(new String(data), type);
                            }
                        },
                        new Serializer<Person>() {

                            @Override
                            public String name() {
                                return "FastJSON";
                            }

                            @Override
                            public byte[] serialize(Person obj) {
                                return JSON.toJSONBytes(obj);
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) {
                                return JSON.parseObject(data, type);
                            }
                        },

                        // ============= JSON-like ==============
                        new Serializer<Person>() {
                            private ObjectMapper mapper = new ObjectMapper(new SmileFactory());

                            @Override
                            public String name() {
                                return "Jackson-smile";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                return mapper.writeValueAsBytes(obj);
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                return mapper.readValue(data, type);
                            }
                        },
                        new Serializer<Person>() {
                            private ObjectMapper mapper = new ObjectMapper(new SmileFactory());
                            {
                                mapper.registerModule(new AfterburnerModule());
                            }

                            @Override
                            public String name() {
                                return "Jackson-smile-afterburner";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                return mapper.writeValueAsBytes(obj);
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                return mapper.readValue(data, type);
                            }
                        },
                        /*new Serializer<Person>() {
                            private ObjectMapper mapper = new ObjectMapper(new SmileFactory());
                            {
                                mapper.registerModule(new DefaultScalaModule());
                            }

                            @Override
                            public String name() {
                                return "Jackson-smile-scala";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                return mapper.writeValueAsBytes(obj);
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                return mapper.readValue(data, type);
                            }
                        },*/
                        new Serializer<Person>() {
                            private ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

                            @Override
                            public String name() {
                                return "Jackson-yaml";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                return mapper.writeValueAsBytes(obj);
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                return mapper.readValue(data, type);
                            }
                        },
                       /* new Serializer<Person>() {
                            private MessagePack msgpack = new MessagePack();
                            {
                                msgpack.register(Person.class);
                            }

                            @Override
                            public String name() {
                                return "MessagePack";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                return msgpack.write(obj);
                            }

                            @Override
                            public Person deserialize(byte[] data, Class type) throws Exception {
                                return msgpack.read(data, Person.class);
                            }
                        },*/

                        // ============= Binary ==============
                        new Serializer<Person>() {
                            private Schema<Person> schema = RuntimeSchema.getSchema(Person.class);
                            private LinkedBuffer buffer = LinkedBuffer.allocate();

                            @Override
                            public String name() {
                                return "Protostuff";
                            }

                            @Override
                            public byte[] serialize(Person obj) {
                                byte[] data = ProtobufIOUtil.toByteArray(obj, schema, buffer);
                                buffer.clear();
                                return data;
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) {
                                Person obj = new Person();
                                ProtobufIOUtil.mergeFrom(data, obj, schema);
                                return obj;
                            }
                        },
                        new Serializer<Person>() {

                            @Override
                            public String name() {
                                return "Hessian";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                                Hessian2Output output = new Hessian2Output(bytes);
                                output.writeObject(obj);
                                output.close(); // flush to avoid EOF error
                                return bytes.toByteArray();
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(data));
                                return (Person) input.readObject();
                            }
                        },
                        new Serializer<Person>() {
                            private FSTObjectInput input = new FSTObjectInput();
                            private FSTObjectOutput output = new FSTObjectOutput();

                            @Override
                            public String name() {
                                return "FST";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                output.resetForReUse();
                                output.writeObject(obj);
                                return output.getCopyOfWrittenBuffer();
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                input.resetForReuseUseArray(data);
                                return (Person) input.readObject();
                            }
                        },
                        new Serializer<Person>() {
                            private Kryo kryo = new Kryo();
                            {
                                kryo.setReferences(false);
                                kryo.setRegistrationRequired(true);
                                kryo.register(Person.class);
                            }
                            private byte[] buffer = new byte[512];
                            private Output output = new Output(buffer, -1);
                            private Input input = new Input(buffer);

                            @Override
                            public String name() {
                                return "Kryo";
                            }

                            @Override
                            public byte[] serialize(Person obj) {
                                output.setBuffer(buffer, -1);   // reset
                                kryo.writeObject(output, obj);
                                return output.toBytes();
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) {
                                input.setBuffer(data);
                                return kryo.readObject(input, type);
                            }
                        },
                        new Serializer<Person>() {

                            @Override
                            public String name() {
                                return "JDK Built-in";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                new ObjectOutputStream(out).writeObject(obj);
                                return out.toByteArray();
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                return (Person) new ObjectInputStream(new ByteArrayInputStream(data)).readObject();
                            }
                        },

                        // ============= XML ==============
                        new Serializer<Person>() {
                            private XStream xstream = new XStream();

                            @Override
                            public String name() {
                                return "XStream";
                            }

                            @Override
                            public byte[] serialize(Person obj) throws Exception {
                                ByteArrayOutputStream out = new ByteArrayOutputStream();
                                xstream.toXML(obj, out);
                                return out.toByteArray();
                            }

                            @Override
                            public Person deserialize(byte[] data, Class<Person> type) throws Exception {
                                return (Person) xstream.fromXML(new ByteArrayInputStream(data));
                            }
                        },
                };

        // Sheet
        int[] testCase = { 10, 100, 1000 };
        String[] sheetNames = new String[testCase.length];
        for (int i = 0; i < sheetNames.length; i++) {
            sheetNames[i] = "Size=" + testCase[i];
        }

        // Row
        String[] rowNames = new String[serializers.length];
        for (int i = 0; i < rowNames.length; i++) {
            rowNames[i] = serializers[i].name();
        }

        // Column
        String[] colNames = new String[3];
        colNames[0] = "Size";
        colNames[1] = "Ser";
        colNames[2] = "Der";

        Reporter reporter = new Reporter(sheetNames, rowNames, colNames);
        for (int i = 0; i < testCase.length; i++) {
            int length = testCase[i];
            System.out.printf("===== Round [%d]: %d =====\n", i, length);

            for (int j = 0; j < serializers.length; j++) {
                testSerializer(reporter, length, i, j, serializers[j]);
            }
        }
        System.out.println(reporter.generateFinalReport());
    }

    private static void testSerializer(Reporter reporter,
                                       int length,
                                       int sheet,
                                       int row,
                                       Serializer<Person> serializer)
            throws Exception {

        System.out.println("===== " + serializer.name() + " =====");

        // 1.Warm-up and validate
        System.out.println("Pre-warmup & Check correctness...");
        Person p1 = new Person();
        for (int i = 0; i < WARMUP_COUNT; i++) {
            byte[] bytes = serializer.serialize(p1);
            Person p2 = serializer.deserialize(bytes, Person.class);
            if (!p1.equals(p2)) {
                throw new IllegalStateException(p1 + " not equals to " + p2);
            }
        }
        int serSize = serializer.serialize(p1).length;
        System.out.printf("%s serialization size[%d]\n", serializer.name(), serSize);
        reporter.report(sheet, row, COL_SER_SIZE, serSize);
        doGc();

        // 2.Serialization
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < TEST_COUNT; i++) {
            serializer.serialize(p1);
        }
        long serCostTime = System.currentTimeMillis() - startTime;
        System.out.printf("%s serialization benchmark[%d]\n", serializer.name(), serCostTime);
        reporter.report(sheet, row, COL_SER_COST, serCostTime);

        // Warm up again
        for (int i = 0; i < WARMUP_COUNT; i++) {
            byte[] bytes = serializer.serialize(p1);
            serializer.deserialize(bytes, Person.class);
        }
        doGc();

        // 3.De-Serialization
        byte[] bytes = serializer.serialize(p1);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < TEST_COUNT; i++) {
            serializer.deserialize(bytes, Person.class);
        }
        long derCostTime = System.currentTimeMillis() - startTime;
        System.out.printf("%s de-serialization benchmark[%d]\n", serializer.name(), derCostTime);
        reporter.report(sheet, row, COL_DER_COST, derCostTime);
        System.out.println();
    }

    public static void doGc() {
        System.gc();
    }

    static class Reporter {
        private final String[] sheetNames;
        private final String[] rowNames;
        private final String[] colNames;
        private final long[][][] table;

        Reporter(String[] sheetNames,
                 String[] rowNames,
                 String[] colNames) {
            this.sheetNames = sheetNames;
            this.rowNames = rowNames;
            this.colNames = colNames;
            this.table = new long[sheetNames.length]
                    [rowNames.length]
                    [colNames.length];
        }

        public void report(int sheet, int row, int col, long val) {
            table[sheet][row][col] = val;
        }

        public String generateFinalReport() {
            StringBuilder report = new StringBuilder();
            for (int i = 0; i < table.length; i++) {
                report.append(StringUtils.center(sheetNames[i], 50, '*'))
                        .append("\n");
                // 1.Header
                final int width0 = 30;
                final int width1 = 10;
                report.append(StringUtils.rightPad("", width0));
                for (String colName : colNames) {
                    report.append(StringUtils.rightPad(colName, width1));
                }
                report.append("\n");

                // 2.Row
                for (int j = 0; j < table[i].length; j++) {
                    report.append(StringUtils.rightPad(rowNames[j], width0));
                    for (int k = 0; k < table[i][j].length; k++) {
                        report.append(StringUtils.rightPad(
                                String.valueOf(table[i][j][k]), width1));
                    }
                    report.append("\n");
                }
                report.append("\n");
            }
            return report.toString();
        }
    }
}
