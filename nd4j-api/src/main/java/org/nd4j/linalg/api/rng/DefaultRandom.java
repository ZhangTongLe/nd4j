package org.nd4j.linalg.api.rng;

import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Apache commons based random number generation
 *
 * @author Adam Gibson
 */
public class DefaultRandom implements Random, RandomGenerator {
    protected RandomGenerator randomGenerator;

    /**
     * Initialize with a System.currentTimeMillis()
     * seed
     */
    public DefaultRandom() {
        this(System.currentTimeMillis());
    }

    public DefaultRandom(long seed) {
        this.randomGenerator = new org.apache.commons.math3.random.SynchronizedRandomGenerator(new MersenneTwister(seed));
    }


    public DefaultRandom(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    @Override
    public void setSeed(int seed) {
        getRandomGenerator().setSeed(seed);
    }

    @Override
    public void setSeed(int[] seed) {
        getRandomGenerator().setSeed(seed);
    }

    @Override
    public void setSeed(long seed) {
        getRandomGenerator().setSeed(seed);
    }

    @Override
    public void nextBytes(byte[] bytes) {
        getRandomGenerator().nextBytes(bytes);
    }

    @Override
    public int nextInt() {
        return getRandomGenerator().nextInt();
    }

    @Override
    public int nextInt(int n) {
        return getRandomGenerator().nextInt(n);
    }

    @Override
    public long nextLong() {
        return getRandomGenerator().nextLong();
    }

    @Override
    public boolean nextBoolean() {
        return getRandomGenerator().nextBoolean();
    }

    @Override
    public float nextFloat() {
        return getRandomGenerator().nextFloat();
    }

    @Override
    public double nextDouble() {
        return getRandomGenerator().nextDouble();
    }

    @Override
    public double nextGaussian() {
        return getRandomGenerator().nextGaussian();
    }

    @Override
    public INDArray nextGaussian(int[] shape) {
        INDArray ret = Nd4j.create(shape);
        INDArray linear = ret.linearView();
        for (int i = 0; i < linear.length(); i++) {
            ret.putScalar(i, nextGaussian());
        }
        return ret;
    }

    @Override
    public INDArray nextDouble(int[] shape) {
        INDArray ret = Nd4j.create(shape);
        INDArray linear = ret.linearView();
        for (int i = 0; i < linear.length(); i++) {
            linear.putScalar(i, nextDouble());
        }
        return ret;
    }

    @Override
    public INDArray nextFloat(int[] shape) {
        INDArray ret = Nd4j.create(shape);
        INDArray linear = ret.linearView();
        for (int i = 0; i < linear.length(); i++) {
            ret.putScalar(i, nextFloat());
        }
        return ret;
    }

    @Override
    public INDArray nextInt(int[] shape) {
        INDArray ret = Nd4j.create(shape);
        INDArray linear = ret.linearView();
        for (int i = 0; i < linear.length(); i++) {
            ret.putScalar(i, nextInt());
        }
        return ret;
    }

    @Override
    public INDArray nextInt(int n, int[] shape) {
        INDArray ret = Nd4j.create(shape);
        INDArray linear = ret.linearView();
        for (int i = 0; i < linear.length(); i++) {
            ret.putScalar(i, nextInt(n));
        }
        return ret;
    }


    public synchronized RandomGenerator getRandomGenerator() {
        return randomGenerator;
    }

}
