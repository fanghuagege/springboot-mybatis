/*
 * Copyright (c) 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grom.mybatis.plugins;

import com.grom.mybatis.plugins.utils.CommentTools;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.JDBCType;
import java.util.List;
import java.util.Properties;

/**
 * ---------------------------------------------------------------------------
 * 查询未删除插件
 * ---------------------------------------------------------------------------
 *
 * @author: yuc
 * ---------------------------------------------------------------------------
 */
public class SelectNotDeletePlugin extends PluginAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SelectNotDeletePlugin.class);

    public static final String METHOD_SELECT_NOT_DELETE = "selectNotDelete";  // 方法名

    public static final String LOGICAL_DELETE_COLUMN_KEY = "logicalDeleteColumn";  // 逻辑删除列-Key
    public static final String LOGICAL_DELETE_VALUE_KEY = "logicalDeleteValue";  // 逻辑删除值-Key

    private IntrospectedColumn logicalDeleteColumn; // 逻辑删除列
    private String logicalDeleteValue;  // 逻辑删除值

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(List<String> warnings) {
        // 插件使用前提是targetRuntime为MyBatis3
        /*if (StringUtility.stringHasValue(getContext().getTargetRuntime()) && "MyBatis3Simple".equalsIgnoreCase(getContext().getTargetRuntime()) == false  ){
            logger.warn(":插件"+this.getClass().getTypeName()+"要求运行targetRuntime必须为MyBatis3！"+getContext().getTargetRuntime());
            return false;
        }*/
        return true;
    }

    /**
     * 初始化阶段
     * 具体执行顺序 http://www.mybatis.org/generator/reference/pluggingIn.html
     *
     * @param introspectedTable
     * @return
     */
    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        logger.info(" 1. 首先获取全局配置");
        Properties properties = getProperties();
        String logicalDeleteColumn = properties.getProperty(LOGICAL_DELETE_COLUMN_KEY);
        this.logicalDeleteValue = properties.getProperty(LOGICAL_DELETE_VALUE_KEY);
        logger.info(" 2. 获取表单独配置，如果有则覆盖全局配置");
        if (introspectedTable.getTableConfigurationProperty(LOGICAL_DELETE_COLUMN_KEY) != null) {
            logicalDeleteColumn = introspectedTable.getTableConfigurationProperty(LOGICAL_DELETE_COLUMN_KEY);
        }
        if (introspectedTable.getTableConfigurationProperty(LOGICAL_DELETE_VALUE_KEY) != null) {
            this.logicalDeleteValue = introspectedTable.getTableConfigurationProperty(LOGICAL_DELETE_VALUE_KEY);
        }
        logger.info("3. 判断该表是否存在逻辑删除列");
        this.logicalDeleteColumn = null;
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : columns) {
            if (column.getActualColumnName().equalsIgnoreCase(logicalDeleteColumn)) {
                // 判断字段类型
                JDBCType type = JDBCType.valueOf(column.getJdbcType());
                if (JDBCType.BIGINT == type
                        || JDBCType.BIT == type
                        || JDBCType.BOOLEAN == type
                        || JDBCType.CHAR == type
                        || JDBCType.DECIMAL == type
                        || JDBCType.DOUBLE == type
                        || JDBCType.FLOAT == type
                        || JDBCType.INTEGER == type
                        || JDBCType.LONGNVARCHAR == type
                        || JDBCType.LONGVARCHAR == type
                        || JDBCType.NCHAR == type
                        || JDBCType.NUMERIC == type
                        || JDBCType.NVARCHAR == type
                        || JDBCType.SMALLINT == type
                        || JDBCType.TINYINT == type
                        || JDBCType.VARCHAR == type) {
                    this.logicalDeleteColumn = column;
                } else {
                    logger.warn("(查询未删除行插件):" + introspectedTable.getFullyQualifiedTable() + "逻辑删除列(" + introspectedTable.getTableConfigurationProperty(LOGICAL_DELETE_COLUMN_KEY) + ")的类型不在支持范围（请使用数字列，字符串列，布尔列）！");
                }
            }
        }

        if (introspectedTable.getTableConfigurationProperty(LOGICAL_DELETE_COLUMN_KEY) != null && this.logicalDeleteColumn == null) {
            logger.warn("(查询未删除行插件):" + introspectedTable.getFullyQualifiedTable() + "没有找到您配置的逻辑删除列(" + introspectedTable.getTableConfigurationProperty(LOGICAL_DELETE_COLUMN_KEY) + ")！");
        }
    }

    /**
     * Java Client Methods 生成
     * 具体执行顺序 http://www.mybatis.org/generator/reference/pluggingIn.html
     *
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (this.logicalDeleteColumn != null) {
            // 1. 逻辑删除ByExample
            Method mSelectNotDelete = new Method(METHOD_SELECT_NOT_DELETE);
            // 返回值类型
            FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
            FullyQualifiedJavaType listType;
            listType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

            returnType.addTypeArgument(listType);
            mSelectNotDelete.setReturnType(returnType);
            // 添加方法说明
            CommentTools.addGeneralMethodComment(mSelectNotDelete, introspectedTable);
            // interface 增加方法
            interfaze.addMethod(mSelectNotDelete);
            logger.debug("(查询未删除行插件):" + interfaze.getType().getShortName() + "selectNotDelete。");
        }

        return true;
    }

    /**
     * SQL Map Methods 生成
     * 具体执行顺序 http://www.mybatis.org/generator/reference/pluggingIn.html
     *
     * @param document
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        if (this.logicalDeleteColumn != null) {
            //数据库表名
            String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();

            // 生成查询语句
            XmlElement selectElement = new XmlElement("select");
            // 添加注释(!!!必须添加注释，overwrite覆盖生成时，@see XmlFileMergerJaxp.isGeneratedNode会去判断注释中是否存在OLD_ELEMENT_TAGS中的一点，例子：@mbg.generated)
            CommentTools.addComment(selectElement);

            // 添加ID
            selectElement.addAttribute(new Attribute("id", "selectNotDelete"));

            // 添加返回类型
            selectElement.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));

            selectElement.addElement(new TextElement("select")); //$NON-NLS-1$

            StringBuilder sb = new StringBuilder();
            sb.append(" * from "); //$NON-NLS-1$
            sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
            selectElement.addElement(new TextElement(sb.toString()));


            sb.setLength(0);
            // 更新逻辑删除字段
            sb.append(" where ");
            sb.append(this.logicalDeleteColumn.getActualColumnName());
            sb.append(" != ");

            // 判断字段类型
            JDBCType type = JDBCType.valueOf(this.logicalDeleteColumn.getJdbcType());
            if (this.logicalDeleteValue == null || "NULL".equalsIgnoreCase(this.logicalDeleteValue)) {
                sb.append("NULL");
            } else if (JDBCType.CHAR == type
                    || JDBCType.LONGNVARCHAR == type
                    || JDBCType.LONGVARCHAR == type
                    || JDBCType.NCHAR == type
                    || JDBCType.NVARCHAR == type
                    || JDBCType.VARCHAR == type) {
                sb.append("'");
                sb.append(this.logicalDeleteValue);
                sb.append("'");
            } else {
                sb.append(this.logicalDeleteValue);
            }

            selectElement.addElement(new TextElement(sb.toString()));


            // 添加到根节点
            document.getRootElement().addElement(selectElement);


            logger.debug("(查询未删除行插件):" + introspectedTable.getMyBatis3XmlMapperFileName() + "增加方法logicalDeleteByPrimaryKey的实现。");
        }

        return true;
    }

}
