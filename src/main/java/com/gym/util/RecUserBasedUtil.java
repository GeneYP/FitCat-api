package com.gym.util;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.net.URL;
import java.util.List;

/**
 * @author GeneYP
 * @version 1.0
 * @date 2022/5/31 22:27
 * @description com.gym.util
 */
public class RecUserBasedUtil {

    private static Logger logger = LoggerFactory.getLogger(RecUserBasedUtil.class);

    public static List<RecommendedItem> recGymType(Integer userId) throws Exception {
        File modelFile = new File("/Users/chenxuejin/Documents/实验报告/毕业设计/Project/api/src/main/resources/intro.csv");
        if(!modelFile.exists()) {
            System.err.println("Please, specify name of file, or put file 'intro.csv' into current directory!");
            System.exit(1);
        }
        /**
         *
         * 装载  数据  文件
         * DataModel 实现储存并为计算机提供所需的所有偏好、用户和物品数据
         *
         */
        DataModel model = new FileDataModel(modelFile);
        /**
         * UserSimilarity 实现给出两个用户之间的相似度
         * 可以从多种可能度量或计算机中选一种作为依赖
         */
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

        // UserNeighborhood 实现   明确与给定用户最相似的一组用户
        UserNeighborhood neighborhood =
                new NearestNUserNeighborhood(40, similarity, model);

        /**
         *
         * 生成推荐引擎
         * Recommender 合并所有的组件为用户推荐物品
         *
         */
        Recommender recommender = new GenericUserBasedRecommender(
                model, neighborhood, similarity);
        // 为用户1 推荐2件  物品
        List<RecommendedItem> recommendations =
                recommender.recommend(userId, 2);

        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
        }

        return recommendations;
    }

}
