package design;

//	How would you design a URL shortening service that is similar to TinyURL?
//	
//	Background:
//	TinyURL is a URL shortening service where you enter a URL such as 
//	https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
//	
//	Requirements:
//	For instance, "http://tinyurl.com/4e9iAk" is the tiny url for the page 
//	"https://leetcode.com/problems/design-tinyurl". The identifier (the highlighted part) 
//	can be any string with 6 alphanumeric characters containing 0-9, a-z, A-Z.
//	Each shortened URL must be unique; that is, no two different URLs can be shortened to the same URL.
//	Note about Questions:
//	Below are just a small subset of questions to get you started. In real world, 
//	there could be many follow ups and questions possible and the discussion is open-ended 
//	(No one true or correct way to solve a problem). If you have more ideas or questions,
//	please ask in Discuss and we may compile it here!
//	
//	Questions:
//	How many unique identifiers possible? Will you run out of unique URLs?
//	Should the identifier be increment or not? Which is easier to design? Pros and cons?
//	Mapping an identifier to an URL and its reversal - Does this problem ring a bell to you?
//	How do you store the URLs? Does a simple flat file database work?
//	What is the bottleneck of the system? Is it read-heavy or write-heavy?
//	Estimate the maximum number of URLs a single machine can store.
//	Estimate the maximum number of queries per second (QPS) for decoding a shortened URL in a single machine.
//	How would you scale the service? For example, a viral link which is shared in social media could result in 
//	a peak QPS at a moment's notice.
//	How could you handle redundancy? i,e, if a server is down, how could you ensure the service is still operational?
//	Keep URLs forever or prune, pros/cons? How we do pruning? (Contributed by @alex_svetkin)
//	What API would you provide to a third-party developer? (Contributed by @alex_svetkin)
//	If you can enable caching, what would you cache and what's the expiry time? (Contributed by @Humandroid)

/**
 * 
 * 需要考虑的问题一：Functionality 如何实现，即用什么样的数据结构，数据库schema
 * 
 * 首先这个系统基本上分为读和写两个部分，并且读的频率比写的频率大
 * 
 * 读：
 * read相对简单。我们只需要从DB里面根据 short url 读出 long url 即可。
 * 所以这个数据库需要一个table, 其schema应该是两个column, 第一个是 ShortUrl, 第二个是LongUrl。
 * 这个table应该是根据ShortUrl建自增长索引的.
 * 我们可以把ShortUrl理解为一个6为的62进制数.
 * 在实际操作中，第一个column依然为十进制的，我们只需要实现一个10进制和62进制的转换即可
 * 
 * 写:
 * 写包含两个层面，第一个层面是如何根据LongUrl得到一个ShortUrl, 这是这个问题的关键。
 * 其次是把这个ShortUrl和LongUrl放到table里面，这个很简单，直接写就是了。
 * 关于如何把LongUrl转换成ShortUrl有两个办法：
 * 	1. 做Hash。把LongUrl通过md5之类的算法hash成ShortUrl.
 * 		pros:	这样可以保证长短url一一对应。 不会浪费我们短url的空间
 * 		cons:	Hash的conflict是不可控的，特别在这种海量数据的情况下，碰撞的可能性很高，这样系统的效率会非常低下
 * 				虽然有很多方法来处理conflict，但是这样会把系统变的相当复杂。
 *	2. 直接对ShortUrl做自增。 比如现在的shortUrl是0001, 那么下一个longUrl我就会返回0002
 *		pros:	不需要做hash，不需要处理碰撞，简单，高效
 *		cons:	不是一一对应，而是一个LongUrl对应多个ShortUrl. 
 * 
 * 在实际应用中，我们会选择第2种方案。因为解决hash碰撞不是一件容易的事情。并且hash本身也需要占用大量的空间。虽然
 * 节省了ShortUrl的空间但是自身hash也占用了大量空间，所以更像是以空间换空间。其次第二种方法里面一对多的关系并不
 * 影响read时的情况, 我们仍然可以通过short url读出正确的long url。但是我们依然需要节省ShortUrl的空间。在解决这个
 * 问题的时候要明白，如果想要完美解决这个问题，那么solution自然就退回到了方法1的做hash去了。所以我们在这并不寻求
 * 完美的解决方案，我们只需要找到一个性价比比较高的，打折的方案即可。
 * 
 * 这个时候我们可以通过LRU来处理。 做法:
 * 	1. 维护一个Long->Short的hashTable，并且为这个table中的键值对设定一个过期时间，比如一小时.
 * 	2. 每当来一个Long的时候，看这个long在这个table里面没有，如果有，那么直接返回，并延长一小时。
 * 	3. 如果没有，拿到一个新的short，并且把这个long->short放到这个table里面，并且设为一小时.
 *  4. 定期删除那些过期的键值对, 注意，此时并不删除DB里面的数据
 * 
 *  这样可以减少重复的情况，但是无法保证完全一对一。
 *  
 *  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 *  
 *  第二个需要考虑的问题： Optimization, 时间上的，空间上的
 *  
 *  在时间上的Optimization:
 *    读和写都可以通过一种方法来进行优化: 分布式.
 *    1. 用多台机器，每台机器管理一个特定的空间，比如机器一的ShortUrl一定是1开头的，机器二是2开头的，等等
 *    2. 读的时候根据ShortUrl找到对应的机器来读
 *    3. 对于写来说，每台机器自己维护一个当前的最大short url，然后对于新来的long，自增长一个即可。
 *    4. 但是要做好平衡，不能全写到一个机器上去了。
 *  
 *  
 *  在空间上的Optimization: 回收short url
 *  	
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 
 * 第三个问题: Stability
 * 
 * 	1. 机器挂了怎么办？ -> 做备份服务器。挂了一个，第二个马上补上
 *  2. 机器挂了的时候，怎么保证正确的存储目前最大ShortUrl
 * 
 * 
 * 第四个问题: Extensibility
 * 
 * 	1. 现在只有62个字符可以用，万一想扩展到更多怎么办
 *  2. 如果客户提出要求，想要特定的字符个数，和特定的可用字符该，该怎么处理
 *  3. 
 * 
 * @author Dingp
 *
 */
public class TinyURL {

}