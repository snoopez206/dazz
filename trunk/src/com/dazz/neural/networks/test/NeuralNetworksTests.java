/**
 * Licensed to the dazz brainy artificial intelligence
 */
package com.dazz.neural.networks.test;

import java.io.File;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.MLMethod;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.factory.MLMethodFactory;
import org.encog.ml.factory.MLTrainFactory;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.obj.SerializeObject;
import org.ofbiz.base.util.Debug;
import org.ofbiz.base.util.FileUtil;
import org.ofbiz.service.testtools.OFBizTestCase;

/**
 * neural networks tests
 * @author chatree
 *
 */
public class NeuralNetworksTests extends OFBizTestCase {
    
    public final static String module = NeuralNetworksTests.class.getName();

    public NeuralNetworksTests(String name) {
        super(name);
    }

    /**
     * test basic layer
     * @throws Exception
     */
    public void testBasicLayer() throws Exception {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(2));
        network.addLayer(new BasicLayer(3));
        network.addLayer(new BasicLayer(1));
        network.getStructure().finalizeStructure();
        network.reset();
    }
    
    /**
     * test activation sigmoid
     * @throws Exception
     */
    public void testActivationSigmoid() throws Exception {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 2));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 3));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
        network.getStructure().finalizeStructure();
        network.reset();
    }
    
    /**
     * test context layer
     * @throws Exception
     */
    public void testContextLayer() throws Exception {
        BasicLayer input = null;
        BasicLayer hidden = null;
        BasicNetwork network = new BasicNetwork();
        network.addLayer(input = new BasicLayer(1));
        network.addLayer(hidden = new BasicLayer(2));
        network.addLayer(new BasicLayer(1));
        input.setContextFedBy(hidden);
        network.getStructure().finalizeStructure();
        network.reset();
    }
    
    /**
     * test train and save
     * @throws Exception
     */
    public void testTrainAndSave() throws Exception {
        Debug.logInfo("Training XOR network to under 1% error rate.", module);
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(2));
        network.addLayer(new BasicLayer(6));
        network.addLayer(new BasicLayer(1));
        network.getStructure().finalizeStructure();
        network.reset();
        
        // create train data set
        final double[][] XOR_INPUT = new double[][] {{1, 1}, {1, 0}};
        final double[][] XOR_IDEAL = new double[][] {{0, 1}, {0, 0}};
        
        // train
        MLDataSet trainingSet = new BasicMLDataSet(XOR_INPUT, XOR_IDEAL);
        final MLTrain train = new ResilientPropagation(network, trainingSet);
        do {
            train.iteration();
        } while (train.getError() > 0.009);
        
        // display error rate
        double error = network.calculateError(trainingSet);
        Debug.logInfo("Network trained to error: " + error, module);
        
        // save network
        Debug.logInfo("Saving network", module);
        File networkFile = FileUtil.getFile("component://dazz/models/network.bin");
        EncogDirectoryPersistence.saveObject(networkFile, network);
        
        // load network
        Debug.logInfo("Loading network", module);
        BasicNetwork network2 = (BasicNetwork) EncogDirectoryPersistence.loadObject(networkFile);
        double error2 = network2.calculateError(trainingSet);
        Debug.logInfo("Loaded network's error is (should be same as above): " + error2, module);
    }
    
    /**
     * test train and save using Java serialize
     * @throws Exception
     */
    public void testTrainAndSaveUsingJavaSerialize() throws Exception {
        Debug.logInfo("Training XOR network to under 1% error rate.", module);
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(2));
        network.addLayer(new BasicLayer(6));
        network.addLayer(new BasicLayer(1));
        network.getStructure().finalizeStructure();
        network.reset();
        
        // create train data set
        final double[][] XOR_INPUT = new double[][] {{1, 1}, {1, 0}};
        final double[][] XOR_IDEAL = new double[][] {{0, 1}, {0, 0}};
        
        // train
        MLDataSet trainingSet = new BasicMLDataSet(XOR_INPUT, XOR_IDEAL);
        final MLTrain train = new ResilientPropagation(network, trainingSet);
        do {
            train.iteration();
        } while (train.getError() > 0.01);
        
        // display error rate
        double error = network.calculateError(trainingSet);
        Debug.logInfo("Network trained to error: " + error, module);
        
        // save network
        Debug.logInfo("Saving network", module);
        File networkFile = FileUtil.getFile("component://dazz/models/network.bin");
        SerializeObject.save(networkFile, network);
        
        // load network
        Debug.logInfo("Loading network", module);
        BasicNetwork network2 = (BasicNetwork) SerializeObject.load(networkFile);
        double error2 = network2.calculateError(trainingSet);
        Debug.logInfo("Loaded network's error is (should be same as above): " + error2, module);
    }
    
    /**
     * test training factory
     * @throws Exception
     */
    public void testTrainingFactory() throws Exception {
        MLMethodFactory methodFactory = new MLMethodFactory();
        MLMethod method = methodFactory.create(MLMethodFactory.TYPE_FEEDFORWARD, "?:B->SIGMOID->4:B->SIGMOID", 2, 1);
        
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(2));
        network.addLayer(new BasicLayer(6));
        network.addLayer(new BasicLayer(1));
        network.getStructure().finalizeStructure();
        network.reset();

        // create train data set
        final double[][] XOR_INPUT = new double[][] {{1, 1}, {1, 0}};
        final double[][] XOR_IDEAL = new double[][] {{0}, {0}};
        
        // train
        MLDataSet dataSet = new BasicMLDataSet(XOR_INPUT, XOR_IDEAL);
        MLTrainFactory trainFactory = new  MLTrainFactory();
        MLTrain train = trainFactory.create(network, dataSet, MLTrainFactory.TYPE_BACKPROP, "LR=0.7,MOM=0.3");
        do {
            train.iteration();
        } while (train.getError() > 0.01);
        
        // display error rate
        double error = network.calculateError(dataSet);
        Debug.logInfo("Network trained to error: " + error, module);
    }
}
